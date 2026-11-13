package com.ems.model;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {
    private int empId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private BigDecimal salary;
    private int deptId;
    private String deptName; // Joined from the departments table for display purposes
    private LocalDate joiningDate;

    public Employee() {}

    // Parameterized Constructor
    public Employee(int empId, String firstName, String lastName, String email, String phone, BigDecimal salary, int deptId, LocalDate joiningDate) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.deptId = deptId;
        this.joiningDate = joiningDate;
    }

    public int getEmpId() {
        return empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
}
