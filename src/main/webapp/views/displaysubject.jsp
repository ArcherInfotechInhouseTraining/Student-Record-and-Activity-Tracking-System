<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<%@ page import="com.arc.model.SubjectModel"  %>
<%@ page import="com.arc.model.SubjectServices" %>
<%@ page import="java.util.List" %>
    
<%
	SubjectServices subjectService = new SubjectServices();
	List<SubjectModel> subjectList = subjectService.selectAllSubjects();
	request.setAttribute("submodel", subjectList);
%>   

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>display subject</title>
    	<link rel="stylesheet" type="text/css" href="../css/style.css">
	</head>
	<body>
		<h1>All Subject List</h1>
		<table border="1">
			<thead>
				<tr>
					<th>SubjectId</th>
					<th>ClassId</th>
					<th>SubjectName</th>
					<th>Marks</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
		
				<c:forEach var="subjects" items="${submodel}">
					<tr>
						<td>${subjects.subId}</td>
						<td>${subjects.classId}</td>
						<td>${subjects.subjectName}</td>
						<td>${subjects.marks}</td>
						<td><a href="../SubjectController?action=edit&sub_id=${subjects.subId}">Edit</a> |
						<a href="../SubjectController?action=delete&sub_id=${subjects.subId}">Delete</a></td>
					
					 </tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>