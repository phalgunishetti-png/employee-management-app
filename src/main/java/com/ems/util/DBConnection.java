package com.ems.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class DBConnection {
        private static final String URL = "jdbc:mysql://localhost:3306/employee_db?useSSL=false&serverTimezone=UTC";
        private static final String USER = "root";
        private static final String PASSWORD = "p@sql"; // Replace with your actual MySQL password

        // Static block to load the MySQL JDBC driver class once at startup
        static {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("MySQL JDBC Driver not found in classpath!", e);
            }
        }

        // Method to establish and return a live connection to MySQL
        public static Connection getConnection() throws SQLException {
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                // Exception Handling: Catch connection failures and wrap them
                throw new SQLException("Database connection failed: " + e.getMessage(), e);
            }
        }
    }