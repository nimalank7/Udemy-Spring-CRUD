package spring.config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // setup connection variables
        String user = "";
        String pass = "";


        String jdbcUrl = "jdbc:postgresql://localhost:5432/employee_directory";
        String driver = "org.postgresql.Driver";

        // get connection to database

        try {
            PrintWriter out = response.getWriter();
            out.println("Connecting to database: " + jdbcUrl);
            Class.forName(driver);

            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass); // user, pass

            out.println("SUCCESS!!!");

            myConn.close();

        } catch (Exception exc) {
            exc.printStackTrace();
            // throw new ServletException(exc);
        }
    }
}
