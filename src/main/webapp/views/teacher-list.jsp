<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teacher List</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<h1>Teacher List</h1>
	<table border="1">
		<thead>
			<tr>
				<th>Teacher ID</th>
				<th>School ID</th>
				<th>Teacher Name</th>
				<th>Teacher Subject</th>
				<th>Teacher Email</th>
				<th>Teacher Contact</th>
				<th>Actions</th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach var="teacher" items="${teacherModel}">
			<tr>
				<td>${teacher.teacherId}</td>
				<td>${teacher.schoolId}</td>
				<td>${teacher.teacherName}</td>
				<td>${teacher.teacherSubject}</td>
				<td>${teacher.teacherEmail}</td>
				<td>${teacher.teacherContact}</td>
				<td>
					<a href="TeacherController?action=edit&teacher_id=${teacher.teacherId}">Edit</a> |
					<a href="TeacherController?action=delete&teacher_id=${teacher.teacherId}">Delete</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table> <br>
	
	<div style="text-align:center">
		<a href="views/addteacher.jsp">Add Teacher</a>
	</div>
	
	
</body>
</html>