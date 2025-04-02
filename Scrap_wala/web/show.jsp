<%@page import="com.company.Connection.MyConnection"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Item Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #4CAF50;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        caption {
            font-size: 1.5em;
            margin: 10px;
        }
    </style>
</head>
<body>
<%
    PrintWriter pw = response.getWriter();
     PreparedStatement ps = null;
    ResultSet rs = null;
    
    try {
    Connection cn = MyConnection.getConnection();
    // Create PreparedStatement
        ps = cn.prepareStatement("SELECT * FROM items");
        rs = ps.executeQuery();
        
        // Start HTML table
        pw.println("<table border='2'>");
        pw.println("<caption><h2>Item Details</h2></caption>");
        pw.println("<tr><th>Id</th><th>Name</th><th>Rate</th>");

        while (rs.next()) {
            String id = rs.getString(1);
            String name = rs.getString(2);
            String rate = rs.getString(3);
            pw.println("<tr>");
            pw.println("<td>" + id + "</td>");
            pw.println("<td>" + name + "</td>");
            pw.println("<td>" + rate + "/kg</td>");
            pw.println("</tr>");
        }
        pw.println("</table>");
        %>
        <a href="Home">Click to home</a>
    <%
        
    } catch (Exception e) {
        pw.println("<div style='color:red;'>" + e.getMessage() + "</div>");
    } finally {
        // Close resources
        if (rs != null) try { rs.close(); } catch (SQLException e) { /* ignored */ }
        if (ps != null) try { ps.close(); } catch (SQLException e) { /* ignored */ }
      
    }
%>
</body>
</html>
