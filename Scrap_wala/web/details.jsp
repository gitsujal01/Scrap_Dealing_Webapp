<%@ page import="com.company.Connection.MyConnection" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Details</title>
    <style>
        /* Your existing styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        header {
            background-color: #007BFF;
            color: white;
            padding: 10px 20px;
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: #fff;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #007BFF;
            color: white;
        }
        caption {
            font-size: 24px;
            margin: 10px;
        }
        h3 {
            color: #d9534f;
            text-align: center;
        }
        a {
            display: inline-block;
            margin: 20px 0;
            padding: 10px 15px;
            background-color: #007BFF;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<header>
    <h1>User Details</h1>
</header>
<%
    String id = request.getParameter("id"); // Correctly retrieve the user ID
    try {
        // Establish connection
        Connection cn = MyConnection.getConnection();
        PreparedStatement ps = cn.prepareStatement("SELECT * FROM user WHERE user_id = ?");
        ps.setString(1, id);
        
        // Execute query
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String name = rs.getString(2);
            String email = rs.getString(3);
            String mobile = rs.getString(4);
            String address = rs.getString(5);
            String city = rs.getString(7);
%>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Mobile</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>Location</th>
                </tr>
                <tr>
                    <td><%= id %></td>
                    <td><%= name %></td>
                    <td><%= email %></td>
                    <td><%= mobile %></td>
                    <td><%= address %></td>
                    <td><%= city %></td>
                    <td><a href="Location.html">Click to see location</a></td>
                </tr>
            </table>
            
            <!-- Button to Send Email -->
            <form action="sendemail.jsp" method="post">
                <input type="hidden" name="userEmail" value="<%= email %>">
                <button type="submit">Send Email</button>
            </form>
            
            <a href="Logout">Click to logout</a>
<%
        } else {
%>
            <h3>No user found with ID: <%= id %></h3>
<%
        }
    } catch (Exception e) {
        out.println("<h3>Error: " + e.getMessage() + "</h3>");
    } 
%>
</body>
</html>
