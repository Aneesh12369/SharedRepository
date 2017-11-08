<%@ page language="java" isELIgnored="false"   contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    
<div class="container">
	<h2 style="color: silver; text-align: center;">Student List</h2>
	<a href="registerStudent.htm"><button type="button" class="btn btn-primary btn-md" >Register</button></a>
	<a href="home.htm"><button type="button" class="btn btn-primary btn-md" >Home</button></a>
	<a href="pdfView.htm?type=pdf"><button type="button" class="btn btn-primary btn-md" >Get PDF</button></a>
	<a href="excelView.htm?type=excel"><button type="button" class="btn btn-primary btn-md" >Get Excel</button></a>
	<c:choose>
		<c:when test="${!empty listStudents}">
			<table class="table">
				<thead class="thead-inverse">
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Address</th>
						<th>Age</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listStudents}" var="student">
						<tr>
							<td class="table-active">${student.id}</td>
							<td class="table-active">${student.name}</td>
							<td class="table-active">${student.address}</td>
							<td class="table-active">${student.age}</td>
							<td class="table-active"><a href="editStudent.htm?id=${student.id}">Edit</td>
							<td class="table-active"><a href="deleteStudent.htm?id=${student.id}">Delete</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>

			<br />
			<br />
			<h3 style="color: teal; text-align: center;">No Records Found</h3>


		</c:otherwise>
	</c:choose>
</div>
</body>

</html>