package com.ems.dao;
import com.ems.model.Employee;
import com.ems.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class EmployeeDAO {

// Fetch all employees using SQL Joins and Java Collections
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>(); // Java Collection to store records

        // SQL Join to fetch department name along with employee details
        String query = "SELECT e.*, d.dept_name FROM employees e LEFT JOIN departments d ON e.dept_id = d.dept_id";

        // Try-with-resources ensures automatic closing of database resources
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("emp_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getBigDecimal("salary"),
                        rs.getInt("dept_id"),
                        rs.getDate("joining_date").toLocalDate()
                );
                emp.setDeptName(rs.getString("dept_name"));
                employees.add(emp); // Add object to Collection list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // Save a new employee using PreparedStatement to prevent SQL Injection
    public boolean saveEmployee(Employee emp) {
        String query = "INSERT INTO employees (first_name, last_name, email, phone, salary, dept_id, joining_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, emp.getFirstName());
            pstmt.setString(2, emp.getLastName());
            pstmt.setString(3, emp.getEmail());
            pstmt.setString(4, emp.getPhone());
            pstmt.setBigDecimal(5, emp.getSalary());
            pstmt.setInt(6, emp.getDeptId());
            pstmt.setDate(7, Date.valueOf(emp.getJoiningDate()));

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Fetch a single employee by ID (used for editing)
    public Employee getEmployeeById(int empId) {
        String query = "SELECT e.*, d.dept_name FROM employees e LEFT JOIN departments d ON e.dept_id = d.dept_id WHERE e.emp_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, empId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Employee emp = new Employee(
                            rs.getInt("emp_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getBigDecimal("salary"),
                            rs.getInt("dept_id"),
                            rs.getDate("joining_date").toLocalDate()
                    );
                    emp.setDeptName(rs.getString("dept_name"));
                    return emp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update an existing employee
    public boolean updateEmployee(Employee emp) {
        String query = "UPDATE employees SET first_name=?, last_name=?, email=?, phone=?, salary=?, dept_id=? WHERE emp_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, emp.getFirstName());
            pstmt.setString(2, emp.getLastName());
            pstmt.setString(3, emp.getEmail());
            pstmt.setString(4, emp.getPhone());
            pstmt.setBigDecimal(5, emp.getSalary());
            pstmt.setInt(6, emp.getDeptId());
            pstmt.setInt(7, emp.getEmpId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an employee by ID
    public boolean deleteEmployee(int empId) {
        String query = "DELETE FROM employees WHERE emp_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, empId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
