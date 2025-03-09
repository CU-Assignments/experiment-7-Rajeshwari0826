// <!DOCTYPE html>
// <html>
// <head>
//     <title>Login Page</title>
// </head>
// <body>
//     <h2>Login</h2>
//     <form action="LoginServlet" method="post">
//         Username: <input type="text" name="username" required><br>
//         Password: <input type="password" name="password" required><br>
//         <input type="submit" value="Login">
//     </form>
// </body>
// </html>


package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) && "password".equals(password)) {
            out.println("<h2>Welcome, " + username + "!</h2>");
        } else {
            out.println("<h2>Invalid Credentials. Please try again.</h2>");
        }
        out.close();
    }
}
