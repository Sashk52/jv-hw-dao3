<%--
  Created by IntelliJ IDEA.
  User: Tsven
  Date: 21.01.2021
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Dear manufacturer, please register here:</h1>
<form method="post" action="${pageContext.request.contextPath}registrationManufacturer" >
    Please, provide name <input type="text" name="name">
    Please, provide country <input type="text" name="country">
    <button type="submit">Register</button>
</form>
</body>
</html>
