<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.arc.model.ClassModel"  %>
<%@ page import="com.arc.model.ClassServices" %>

<%@ page import="com.arc.model.SchoolServices"%>
<%@ page import="com.arc.model.SchoolModel"%>

<%@ page import="java.util.List" %>


<%
	ClassServices classservice = new ClassServices();
	List<ClassModel> classmodelList = classservice.selectAllClass();
	request.setAttribute("classmodel", classmodelList);
	
	SchoolServices schoolService = new SchoolServices();
	List<SchoolModel> schoolModellist = schoolService.selectAllSchools();
	request.setAttribute("schoolModellist", schoolModellist);
	System.out.println("School Service Size"+schoolModellist.size());
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Enter Subject</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
	<h1>Enter Subject Details</h1>

	<form action="SubjectController">
	
		<input type="hidden" name="action"
			value="${subjectModel == null ? 'insert' : 'update' }">

		<c:if test="${subjectModel != null}">
			<input type="hidden" name="subjectId" value="${subjectModel.subId}">
		</c:if>
		
		<label for="schoolname">Select School Name:</label>
         <select id="schoollist" name="schoollist">
         	<c:forEach var="schools" items="${schoolModellist}">
         		<option value="${schools.schoolId}">${schools.schoolName}</option>
         	</c:forEach>
         </select><br><br>
		
		<label>Enter Subject Name</label> 
		<input type="text" name="subjectName" value="${subjectModel != null ? subjectModel.subjectName : ''}"/><br><br> 
		<label>Choose Class:</label> 
		
		<!-- for value set class id instead of class name -->
		<select name="classId">	
			<c:forEach var="cmodel" items="${classmodel}">
				<option value="${cmodel.classId}">${cmodel.className}</option>
			</c:forEach>
		</select><br><br>
		
		<label>Subject Mark</label> 
		<input type="text" name="subjectMark" value="${subjectModel != null ? subjectModel.marks : ''}"><br><br> 
		<button type="submit">Submit</button>
	</form>
</body>
</html>