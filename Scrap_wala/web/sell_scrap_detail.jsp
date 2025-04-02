<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sell Scrap Details</title>
    <style>
        /* Global styles */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            margin-top: 50px;
            color: #007BFF;
        }

        /* Table styling */
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        th {
            background-color: #007BFF;
            color: white;
        }

        td input[type="text"] {
            padding: 8px;
            border: 1px solid #ddd;
            width: 100px;
            text-align: center;
        }

        /* Form styling */
        form {
            text-align: center;
            margin-top: 20px;
        }

        input[type="submit"] {
            padding: 12px 24px;
            background-color: #28a745;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        /* Responsive styling */
        @media screen and (max-width: 768px) {
            table {
                width: 100%;
            }
            td input[type="text"] {
                width: 80px;
            }
        }
    </style>
</head>
<body>

    <h1>Check Expected Weight of your Scrap</h1>

    <%
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Make the connection object
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scrap_pro", "root", "");

            // Create a prepared statement to get the item list
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM items");
            ResultSet rs = ps.executeQuery();
    %>

    <form action="check_sell_scrap.jsp">
        <table>
            <thead>
                <tr>
                    <th>Item</th>
                    <th>Weight (kg)</th>
                </tr>
            </thead>
            <tbody>
                <%
                while (rs.next()) {
                    String itemName = rs.getString(2); 
                %>
                    <tr>
                        <td><%= itemName %></td>
                        <td><input type="text" name="weight_<%= itemName %>" placeholder="Enter weight" required /></td>
                    </tr>
                <%
                }
                %>
            </tbody>
        </table>
        <input type="submit" value="Check" />
    </form>


    <%
        // Close the connection
        cn.close();
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    %>

</body>
</html>
