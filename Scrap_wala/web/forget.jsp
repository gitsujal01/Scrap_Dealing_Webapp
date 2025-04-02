<%-- 
    Document   : forget
    Created on : Oct 22, 2024, 7:41:03 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<% 
            String email = request.getParameter("mail");
        %>  
        <h1>Forget Password</h1>
        <form action="Forgott" method="post">
            <input type="email" name="mail" placeholder="Enter your email" value="<%=email%>" readonly>
            <input type="password" id="pwd1" name="pwd" placeholder="Enter new password">
            <input type="password" id="pwd2" placeholder="Confirm new password">
            <input type="submit" value="Update" onclick="return fun()">
            <div id="one" class="error"></div>
        </form>
        <script> 
            function fun() {
                var id1 = document.getElementById("pwd1").value;
                var id2 = document.getElementById("pwd2").value;
                if (id1 === id2) {
                    return true;
                } else {
                    document.getElementById("one").innerHTML = "Password and confirm password do not match";
                    return false;
                }
            }  
        </script>
    </body>
</html>
