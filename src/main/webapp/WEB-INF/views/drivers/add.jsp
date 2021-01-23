<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Dear driver, please register here:</h1>
<form method="post" action="${pageContext.request.contextPath}add" >
    Please, provide name <input type="text" name="name">
    Please, provide licence number <input type="text" name="licenceNumber">
    Please, provide login <input type="text" name="login">
    Please, provide password <input type="text" name="password">
    Please, repeat password <input type="text" name="repeat_password">
    <button type="submit">Register new driver</button>
    <a href="/drivers/login">Driver Login</a><br>
</form>
</body>
</html>
