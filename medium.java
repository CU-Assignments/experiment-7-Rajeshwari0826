// mysql
// CREATE DATABASE Company;
// USE Company;

// CREATE TABLE employees (
//     id INT PRIMARY KEY AUTO_INCREMENT,
//     name VARCHAR(100),
//     department VARCHAR(50),
//     salary DECIMAL(10,2)
// );

// INSERT INTO employees (name, department, salary) VALUES
// ('John Doe', 'IT', 60000),
// ('Jane Smith', 'HR', 55000),
// ('Alice Johnson', 'Finance', 65000);



// employee search .jsp code
// <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
// <!DOCTYPE html>
// <html>
// <head>
//     <title>Employee Records</title>
// </head>
// <body>
//     <h2>Employee List</h2>
//     <form action="EmployeeServlet" method="post">
//         Search Employee by ID: <input type="text" name="empId">
//         <input type="submit" value="Search">
//     </form>
// </body>
// </html>


package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Company";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String empId = request.getParameter("empId");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            String query = "SELECT * FROM employees WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(empId));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                out.println("<h2>Employee Details</h2>");
                out.println("ID: " + rs.getInt("id") + "<br>");
                out.println("Name: " + rs.getString("name") + "<br>");
                out.println("Department: " + rs.getString("department") + "<br>");
                out.println("Salary: " + rs.getDouble("salary") + "<br>");
            } else {
                out.println("<h2>No Employee Found</h2>");
            }
            conn.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
