<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.arc.model.SchoolServices"%>
<%@ page import="com.arc.model.SchoolModel"%>
<%@ page import="java.util.*"%>
    
<%
	SchoolServices schoolServiceforTeacher = new SchoolServices();
	List<SchoolModel> schoolModellistforteacher = schoolServiceforTeacher.selectAllSchools();
	request.setAttribute("schoolModellistforteacher", schoolModellistforteacher);
	System.out.println("School Service Size"+schoolModellistforteacher.size());
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teacher Add</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<h1>Teacher Form</h1>
	
		<form action="${pageContext.request.contextPath}/TeacherController" method="post">
			
			<input type="hidden" name="action" value="${teachermodel==null ? 'insertteacher' : 'updateteacher'}"/>
			
			<c:if test="${teachermodel!=null}">
				<input type="hidden" name="teacherid" value="${teachermodel.teacherId}"/>
			</c:if>
				
            <br><label for="name">Teacher Name:</label>
			<input type="text" name="teacher_name" placeholder="Enter Teacher Name" value="${teachermodel!=null?teachermodel.teacherName:''}" required/>
			
            <br><label for="subject">Teacher Subject:</label>
			<input type="text" name="teacher_subject" placeholder="Enter Teacher Subject" value="${teachermodel!=null?teachermodel.teacherSubject:''}" required/>
			
            <br><label for="email">Teacher Email:</label>
            <input type="email" name="teacher_email" placeholder="Enter Teacher Email" value="${teachermodel!=null?teachermodel.teacherEmail:''}" required/>
			
            <br><label for="contact">Teacher Contact:</label>
            <input type="text" name="teacher_contact" placeholder="Enter Teacher contact" value="${teachermodel!=null?teachermodel.teacherContact:''}" required/>
			
			<br><label for="schoolname">Select School Name:</label>
            <select id="schoollistforteacher" name="schoollistforteacher">
            	<c:forEach var="schoolsforteacher" items="${schoolModellistforteacher}">
            		<option value="${schoolsforteacher.schoolId}">${schoolsforteacher.schoolName}</option>
            	</c:forEach>
            </select><br><br>
			
			<button type="submit">Submit</button>
		</form><br>
		
		<div style="text-align:center">
			<a href="${pageContext.request.contextPath}/TeacherController" style="text-align:center;">View Teacher List</a>
		</div>
</body>
</html>