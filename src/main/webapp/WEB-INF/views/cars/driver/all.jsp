<%--
  Created by IntelliJ IDEA.
  User: Sashk
  Date: 26.01.2021
  Time: 0:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>All cars by driver</h1>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Model</th>
        <th>Manufacturer</th>
    </tr>
    <c:forEach var="car" items="${carsByDriver}">
        <tr>
            <td>
                <c:out value="${car.id}"/>
            </td>
            <td>
                <c:out value="${car.model}"/>
            </td>
            <td>
                <c:out value="${car.manufacturer}"/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
