<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello mates!</h1>
<a href="${pageContext.request.contextPath}/injectData">Inject test data into the DB</a>
<h4>Drives block</h4>
<a href="/drivers/all">AllDrivers</a><br>
<a href="/drivers/registrationDriver">AddDriver</a><br>
<h4>Manufacturer block</h4>
<a href="/manufactirers/all">AllManufactures</a><br>
<a href="/manufactirers/registrationManufacturer">AddManufacturer</a><br>
<h4>Car block</h4>
<a href="/cars/all">AllCars</a><br>
<a href="/cars/registrationCar">AddCar</a><br>



</body>
</html>
