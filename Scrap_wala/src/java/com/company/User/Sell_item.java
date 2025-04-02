package com.company.User;

import com.company.Connection.MyConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "Sell_item", urlPatterns = {"/Sell_item"})
public class Sell_item extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get form data
            String item1 = request.getParameter("i1");
            String weight1 = request.getParameter("w1");
            String item2 = request.getParameter("i2");
            String weight2 = request.getParameter("w2");
            String item3 = request.getParameter("i3");
            String weight3 = request.getParameter("w3");
            String item4 = request.getParameter("i4");
            String weight4 = request.getParameter("w4");
            String item5 = request.getParameter("i5");
            String weight5 = request.getParameter("w5");

            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scrap_pro", "root", "");

            // Prepare SQL query for insertion
            String query = "INSERT INTO sell (item, weight) VALUES (?, ?)";

            // Using PreparedStatement
            PreparedStatement ps = cn.prepareStatement(query);

            // Insert items with weights
            ps.setString(1, item1);
            ps.setString(2, weight1);
            ps.executeUpdate();

            ps.setString(1, item2);
            ps.setString(2, weight2);
            ps.executeUpdate();

            ps.setString(1, item3);
            ps.setString(2, weight3);
            ps.executeUpdate();

            ps.setString(1, item4);
            ps.setString(2, weight4);
            ps.executeUpdate();

            ps.setString(1, item5);
            ps.setString(2, weight5);
            ps.executeUpdate();

            // If the insertions were successful, redirect to the completion page
            response.sendRedirect("Complete.html");

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
