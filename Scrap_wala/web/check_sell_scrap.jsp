<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Check Sell Scrap</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f9f9f9;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .total-price {
            margin-top: 20px;
            font-size: 20px;
            font-weight: bold;
            text-align: center;
        }
        .total-price span {
            font-size: 24px;
            color: black;
        }
    </style>
</head>
<body>

    <h1>Expected Weight and Price</h1>

    <%
        double grandtotal = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scrap_pro", "root", "");
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM items");
            ResultSet rs = ps.executeQuery();
    %>

    <table>
        <tr>
            <th>Item</th>
            <th>Rate (per kg)</th>
            <th>Weight (kg)</th>
            <th>Price</th>
        </tr>

        <%
        while (rs.next()) {
            String itemName = rs.getString(2);
            String rate = rs.getString(3);
            String item_weight = request.getParameter("weight_" + itemName);
            double i_w =  Double.parseDouble(item_weight);
            double r = Double.parseDouble(rate);
            double t = r * i_w;
            grandtotal += t;
        %>
        <tr>
            <td><%= itemName %></td>
            <td><%= rate %></td>
            <td><%= item_weight %></td>
            <td><%= t %></td>
        </tr>
        <%
        }
        %>
    </table>

    <div class="total-price">
        <span>Total Price = Rs. <%= grandtotal %></span>
    </div>
            <a href ="Home" >Click to home</a>
    <%
        cn.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    %>

</body>
</html>
