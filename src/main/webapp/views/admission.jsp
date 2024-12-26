<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.arc.model.ClassModel"  %>
<%@ page import="com.arc.model.ClassServices" %>
<%@ page import="com.arc.model.TeacherModel"  %>
<%@ page import="com.arc.model.TeacherServices" %>
<%@ page import="java.util.List" %>

<%@ page import="com.arc.model.SchoolServices"%>
<%@ page import="com.arc.model.SchoolModel"%>


<%
	//for class name list
	ClassServices classservice = new ClassServices();
	List<ClassModel> classmodelList = classservice.selectAllClass();
	request.setAttribute("classmodel", classmodelList);
	
	SchoolServices schoolService = new SchoolServices();
	List<SchoolModel> schoolModellist = schoolService.selectAllSchools();
	request.setAttribute("schoolModellist", schoolModellist);
	System.out.println("School Service Size"+schoolModellist.size());
%>

<%
	// for mentor/teacher list
	TeacherServices teacherServices = new TeacherServices();
	List<TeacherModel> teacherModelList = teacherServices.selectAllTeachers();
	request.setAttribute("teacherModel", teacherModelList);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Admission Form</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	</head>
	<body>
		<h1>Admission Form</h1>
		<form action="AdmissionController">
			<input type="hidden" name="action" value="insert">
			<label>Enquiry Id</label>
			<input type="text" name="enquiryId" value="${emodel.enquiryId}"><br><br>
			<label>School Id</label>
			<%-- <input type="text" name="schoolId" value="${emodel.schoolId}"><br><br>
			<label>Enter Your Data Of Birth</label> --%>
			
			<label for="schoolname">Select School Name:</label>
         <select id="schoollist" name="schoolId">
         	<c:forEach var="schools" items="${schoolModellist}">
         		<option value="${schools.schoolId}">${schools.schoolName}</option>
         	</c:forEach>
         </select><br><br>
			<input type="date" name="dob" placeholder="Enter Your DOB"><br><br>
			<label>Enter Your Address</label>
			<input type="text" name="address" placeholder="Enter Your Address"><br><br>
			<label>Enter Your Marks</label>
			<input type="text" name="marks" placeholder="Enter Your Marks"><br><br>
			
			<label>Choose Your Mentor</label>
			<select name="mentor">
				<c:forEach var="tmodel" items="${teacherModel}">
					<option value="${tmodel.teacherName}">${tmodel.teacherName}</option>
				</c:forEach>
			</select><br><br>
			
			<label>Choose Your Class</label>
			<!-- for value set class id instead of class name -->
			<select name="className">	
				<c:forEach var="cmodel" items="${classmodel}">
					<option value="${cmodel.className}">${cmodel.className}</option>
				</c:forEach>
			</select><br><br>
			
			<button type="submit">Submit</button>
			
		</form>
	</body>
</html>