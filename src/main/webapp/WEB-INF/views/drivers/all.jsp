<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>All drivers page</h1>
<table border="1">
    <tr>
        <th>Name</th>
        <th>LicenceNumber</th>
    </tr>
    <c:forEach var="driver" items="$(drivers)"/>
    <tr>
        <td>
            <c:out value="$(driver.id"/>
        </td>
        <td>
            <c:out value="$(driver.licenceNumber"/>
        </td>
    </tr>
    </c: forEach>
</table>
</body>
</html>
