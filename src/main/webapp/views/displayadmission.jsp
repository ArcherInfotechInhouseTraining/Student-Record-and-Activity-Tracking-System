<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<%@ page import="com.arc.model.AdmissionModel"  %>
<%@ page import="com.arc.model.AdmissionServices" %>
<%@ page import="java.util.List" %>
    
<%
	AdmissionServices admissionService = new AdmissionServices();
	List<AdmissionModel> studentList = admissionService.displayAllStudent();
	request.setAttribute("studentmodel", studentList);
%>   

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>display student</title>
    	<link rel="stylesheet" type="text/css" href="../css/style.css">
	</head>
	<body>
		<h1>All Student Details</h1>
		<table border="1">
			<thead>
				<tr>
					<th>AdmissionId</th>
					<th>EnquiryId</th>
					<th>SchoolId</th>
					<th>DOB</th>
					<th>ADDRESS</th>
					<th>Marks</th>
					<th>Mentor</th>
					<th>ClassName</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
		
				<c:forEach var="student" items="${studentmodel}">
					<tr>
						<td>${student.admissionId}</td>
						<td>${student.enquiryId}</td>
						<td>${student.schoolId}</td>
						<td>${student.dob}</td>
						<td>${student.address}</td>
						<td>${student.marks}</td>
						<td>${student.mentor}</td>
						<td>${student.class_name}</td>
						<%-- <td><a href="SubjectController?action=edit&admission_id=${student.admissionId}">Edit</a> --%> 
						<td><a href="SubjectController?action=delete&admission_id=${student.admissionId}">Delete</a></td>
					 </tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>