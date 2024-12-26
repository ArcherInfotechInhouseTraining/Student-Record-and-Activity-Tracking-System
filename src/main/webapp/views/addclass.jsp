<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.arc.model.SchoolServices"%>
<%@ page import="com.arc.model.SchoolModel"%>
<%@ page import="java.util.*"%>

<%
	SchoolServices schoolService = new SchoolServices();
	List<SchoolModel> schoolModellist = schoolService.selectAllSchools();
	request.setAttribute("schoolModellist", schoolModellist);
	System.out.println("School Service Size"+schoolModellist.size());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Class Add</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
		<h1>Class Form</h1>
		<form action="${pageContext.request.contextPath}/ClassController" method="post">
			
			<input type="hidden" name="action" value="${classmodel==null ? 'insertclass' : 'update'}"/>
			
			<c:if test="${classmodel!=null}">
				<input type="hidden" name="classid" value="${classmodel.classId}"/>
			</c:if>
			
			<%-- <c:if test="${schoolModellist!=null}">
				<p>SchoolList is Not Null</p>
			</c:if> --%>
			
			<label for="schoolname">Select School Name:</label>
            <select id="schoollist" name="schoollist">
            	<c:forEach var="schools" items="${schoolModellist}">
            		<option value="${schools.schoolId}">${schools.schoolName}</option>
            	</c:forEach>
            </select>
            
            <label for="name">Class Name:</label>
			<input type="text" name="class_name" placeholder="Enter Class Name" value="${classmodel!=null?classmodel.className:''}" required/>
			
            <label for="fees">Class Fees:</label>
			<input type="text" name="class_fees" placeholder="Enter Class Fees" value="${classmodel!=null?classmodel.classFees:''}" required/>
			
			<br><br><button type="submit">Submit</button>
			
		</form><br>
		
		<div style="text-align:center">
			<a href="${pageContext.request.contextPath}/ClassController">View Class List</a>
			<a href="addteacher.jsp">Add Teacher</a>
		</div>
</body>
</html>