<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>StudentPortal</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<h1 style="color: red; text-align: center">Enter Student Details</h1>
	
	<form:form method="POST" commandName="registerCommand">
  Number: <form:input path="id" readonly="true" disabled="true" />
		<br>
  name: <form:input path="name" /><form:errors path="name" cssStyle="color:red;"/>
		<br>
  Address: <form:input path="address" /><form:errors path="address" cssStyle="color:red;"/>
		<br>
  Age: <form:input path="age" /><form:errors path="age" cssStyle="color:red;"/>
		<br>
		<input type="Submit" value="register" />
	</form:form>
	<br>

	<a href="home.htm"><button type="button" class="btn btn-info"
			style="margin-left: 560px;">Home</button></a>
</body>

</html>

