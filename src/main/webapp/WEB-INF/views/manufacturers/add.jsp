<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Dear manufacturer, please register here:</h1>
<form method="post" action="${pageContext.request.contextPath}add" >
    Please, provide name <input type="text" name="name">
    Please, provide country <input type="text" name="country">
    <button type="submit">Register</button>
</form>
</body>
</html>
