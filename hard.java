// CREATE DATABASE College;
// USE College;

// CREATE TABLE attendance (
//     studentId INT PRIMARY KEY,
//     name VARCHAR(100),
//     status VARCHAR(20)
// );

// <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
// <!DOCTYPE html>
// <html>
// <head>
//     <title>Student Attendance</title>
// </head>
// <body>
//     <h2>Enter Attendance</h2>
//     <form action="AttendanceServlet" method="post">
//         Student ID: <input type="text" name="studentId" required><br>
//         Name: <input type="text" name="name" required><br>
//         Status: 
//         <select name="status">
//             <option value="Present">Present</option>
//             <option value="Absent">Absent</option>
//         </select><br>
//         <input type="submit" value="Submit">
//     </form>
// </body>
// </html>

package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/College";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        String name = request.getParameter("name");
        String status = request.getParameter("status");

        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO attendance (studentId, name, status) VALUES (?, ?, ?)");
            stmt.setInt(1, Integer.parseInt(studentId));
            stmt.setString(2, name);
            stmt.setString(3, status);
            stmt.executeUpdate();
            conn.close();
            response.getWriter().println("Attendance recorded successfully.");
        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
