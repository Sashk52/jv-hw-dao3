<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Login page</h1>
<h4 style="color: red">${errorMessage}</h4>
<form action="${pageContext.request.contextPath}login"method="post">
    Please provide your login:<input type="text" name="driver_login"><br>
    Please provide your password:<input type="password" name="driver-password"><br>
    <button type="submit">Login</button><br>
    <a href="/drivers/add">Registration</a><br>
</form>
</body>
</html>
