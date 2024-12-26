<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<%@ page import="com.arc.model.EnrollmentModel"  %>
<%@ page import="com.arc.model.EnrollmentServices" %>
<%@ page import="java.util.List" %>
    
<%
	EnrollmentServices enrollmentService = new EnrollmentServices();
	List<EnrollmentModel> enrollmentList = enrollmentService.displayEnrolledDetails();
	request.setAttribute("enrollmentList", enrollmentList);
%>   

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>display subject</title>
    	<link rel="stylesheet" type="text/css" href="../css/style.css">
	</head>
	<body>
		<h1>All Enrolled Student List</h1>
		<table border="1">
			<thead>
				<tr>
					<th>EnquiryId</th>
					<th>SchoolId</th>
					<th>Name</th>
					<th>Email</th>
					<th>Contact</th>
				</tr>
			</thead>
			<tbody>
		
				<c:forEach var="student" items="${enrollmentList}">
					<tr>
						<td>${student.enquiryId}</td>
						<td>${student.schoolId}</td>
						<td>${student.studentName}</td>
						<td>${student.studentEmail}</td>
						<td>${student.studentContact}</td>
						<%-- <td><a href="SubjectController?action=edit&admission_id=${student.admissionId}">Edit</a>  
						<td><a href="SubjectController?action=delete&admission_id=${student.admissionId}">Delete</a></td>--%>
					 </tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>