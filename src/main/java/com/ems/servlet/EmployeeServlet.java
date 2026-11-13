package com.ems.servlet;

import com.ems.dao.EmployeeDAO;
import com.ems.model.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    private EmployeeDAO employeeDAO;

    @Override
    public void init() {
        employeeDAO = new EmployeeDAO();
    }

    // Handles HTTP GET: Fetches all employees and forwards to JSP view
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "delete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    employeeDAO.deleteEmployee(deleteId);
                    response.sendRedirect("employees");
                    break;
                case "edit":
                    int editId = Integer.parseInt(request.getParameter("id"));
                    Employee existingEmp = employeeDAO.getEmployeeById(editId);
                    request.setAttribute("employee", existingEmp);
                    request.setAttribute("employees", employeeDAO.getAllEmployees());
                    request.getRequestDispatcher("/WEB-INF/views/employee-list.jsp").forward(request, response);
                    break;
                default:
                    request.setAttribute("employees", employeeDAO.getAllEmployees());
                    request.getRequestDispatcher("/WEB-INF/views/employee-list.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String empIdStr = request.getParameter("empId");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            BigDecimal salary = new BigDecimal(request.getParameter("salary"));
            int deptId = Integer.parseInt(request.getParameter("deptId"));
            LocalDate joiningDate = LocalDate.parse(request.getParameter("joiningDate"));

            if (empIdStr == null || empIdStr.isEmpty() || empIdStr.equals("0")) {
                // Insert new employee
                Employee emp = new Employee(0, firstName, lastName, email, phone, salary, deptId, joiningDate);
                employeeDAO.saveEmployee(emp);
            } else {
                // Update existing employee
                int empId = Integer.parseInt(empIdStr);
                Employee emp = new Employee(empId, firstName, lastName, email, phone, salary, deptId, joiningDate);
                employeeDAO.updateEmployee(emp);
            }

            response.sendRedirect("employees");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error processing form: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

}
