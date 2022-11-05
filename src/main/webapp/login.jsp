<%--
  Created by IntelliJ IDEA.
  User: abdes
  Date: 21/10/2022
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
</head>
<body>
<div align="center">
    <h1>Admin Login</h1>
    <form method="post">
        <table style="with: 80%">
            <tr>
                <td>Email</td>
                <td><input type="email" name="Email" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="Password" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>

