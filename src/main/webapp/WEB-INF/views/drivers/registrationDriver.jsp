<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Dear driver, please register here:</h1>
<form method="post" action="${pageContext.request.contextPath}registrationDriver" >
    Please, provide name <input type="text" name="name">
    Please, provide lisence number <input type="text" name="lisenceNumber">
    <button type="submit">Register</button>
</form>
</body>
</html>
