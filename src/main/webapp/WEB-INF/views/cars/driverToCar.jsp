<%--
  Created by IntelliJ IDEA.
  User: Tsven
  Date: 21.01.2021
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<h1>Add driver to car page</h1>
<form method="post" action="${pageContext.request.contextPath}driverToCar" >
    Please, provide driver_id <input type="number" name="driver_id">
    Please, provide car_id <input type="number" name="car_id">
    <button type="submit">Register</button>
</form>
</table>
</html>
