<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-image: url('Images/disco.png'); /* Add your image path here */
                background-size: cover;
                background-position: center;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .login-container {
                background-color: rgba(255, 255, 255, 0.8); /* Slight transparency for better readability */
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0,0,0,0.1);
                width: 300px;
            }
            h2 {
                text-align: center;
                color: #333;
            }
            input[type="email"],
            input[type="password"] {
                width: 100%;
                padding: 10px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            .btn {
                width: 100%;
                padding: 10px;
                background-color: #28a745;
                border: none;
                border-radius: 4px;
                color: white;
                font-size: 16px;
                cursor: pointer;
            }
            .btn:hover {
                background-color: #218838;
            }
            .link {
                display: block;
                text-align: center;
                margin-top: 10px;
                color: #007bff;
                text-decoration: none;
            }
            .link:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="login-container">
            <h2>User Login</h2>
            <form action="Login" method="POST">
                <input type="email" name="email" placeholder="Enter your email" required>
                <input type="password" name="password" placeholder="Enter your password" required>
                <button type="submit" class="btn">Login</button>
            </form>
            <a href="Register.html" class="link">Register</a>
            <a href="Forgot_pwd.html">Forgot Password?</a>
        </div>
    </body>
</html>
