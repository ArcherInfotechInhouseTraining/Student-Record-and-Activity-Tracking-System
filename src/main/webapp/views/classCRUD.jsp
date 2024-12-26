<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<h1>Class List</h1>
	<table border="1">
			<thead>
				<tr>
					<th>Class ID</th>
					<th>School ID</th>
					<th>Class Name</th>
					<th>Class Fees</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="class_model" items="${classmodel}">
				<tr>
					<td>${class_model.classId}</td>
					<td>${class_model.schoolId}</td>
					<td>${class_model.className}</td>
					<td>${class_model.classFees}</td>
					<td>
						<a href="ClassController?action=edit&class_id=${class_model.classId}">Edit</a> |
						<a href="ClassController?action=delete&class_id=${class_model.classId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table> <br>
		
	<div style="text-align:center">
		<a href="views/addclass.jsp">Add Class</a>
	</div>
	
</body>
</html>