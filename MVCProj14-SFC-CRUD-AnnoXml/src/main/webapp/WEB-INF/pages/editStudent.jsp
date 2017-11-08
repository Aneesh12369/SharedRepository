<%@ page language="java" isELIgnored="false"   contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>StudentPortal</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    
<h1 style="color:red;text-align:center">Edit Student Details </h1>
<form:errors path="*"/>
<form:form method="POST" commandName="editStudentCommand">
  Number: <form:input path="id" readonly="true" disabled="true"/><br>
  name: <form:input path="name"/><br><form:errors path="name" cssStyle="color:red;"/>
  Address: <form:input path="address"/><br><form:errors path="address" cssStyle="color:red;"/>
  Age: <form:input path="age"/><br><form:errors path="age" cssStyle="color:red;"/>
  <input type="Submit"  value="edit"/>
</form:form>
<br>
<a href="listStudents.htm"><button type="button" class="btn btn-info" style="margin-left: 560px;">GetAllStudents</button></a>
</body>

</html>

