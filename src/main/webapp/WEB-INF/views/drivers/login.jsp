<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Login page</h1>
<form action="${pageContext.request.contextPath}/login"method="post">
    Please provide your login:<input type="text" name="login">
    Please provide your password:<input type="password" name="pwd">
    <button type="submit">Login</button>
    <a href="/drivers/add">Registration</a>
</form>
</body>
</html>
