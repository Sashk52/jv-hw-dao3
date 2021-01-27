<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Dear driver, please register here:</h1>
<form method="post" action="${pageContext.request.contextPath}add" >
    Please, provide name <input type="text" name="name"><br>
    Please, provide licence number <input type="text" name="licenceNumber"><br>
    Please, provide login <input type="text" name="login"><br>
    Please, provide password <input type="text" name="password"><br>
    Please, repeat password <input type="text" name="repeat_password"><br>
    <button type="submit">Register new driver</button><br>
    <a href="/drivers/login">Driver Login</a><br>
</form>
</body>
</html>
