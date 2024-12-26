<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.arc.model.SchoolModel"%>
<%@ page import="com.arc.model.EnrollmentModel"%>
<%@ page import="com.arc.model.AdmissionModel"%>
<%@ page import="com.arc.model.EnrollmentServices"%>
<%@ page import="com.arc.model.AdmissionServices"%>
<%@ page import="com.arc.model.ClassServices"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
EnrollmentServices enrollmentServices = new EnrollmentServices();
List<EnrollmentModel> students = enrollmentServices.selectStudent();

AdmissionServices admitionServices = new AdmissionServices();
List<AdmissionModel> addmited_students = admitionServices.displayAllStudent();

request.setAttribute("addmited_students", addmited_students);
request.setAttribute("students", students);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
	<style type="text/css">
	
	.container{
		display:flex;
	}
	.enquired_student {
	display: flex;
    flex-direction: column;
 width: -webkit-fill-available;
    align-items: center;
	}
	.addmited_student{
	
	width: -webkit-fill-available;
	display: flex;
    flex-direction: column;
    align-items: center;
	}
	
	</style>
</head>

<body>
	<div class="container">
		<div class="enquired_student">
		
		
		
		<h1>Enquired Student Contact List</h1>
		<table>
			<thead>
				<tr>
					<th>Student ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Contact</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="student" items="${students}">
					<tr>
						<td>${student.enquiryId}</td>
						<td>${student.studentName}</td>
						<td>${student.studentEmail}</td>
						<td>${student.studentContact}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
</div>

	<div class="addmited_student">
	
	
	
	
		<h1>Admited Student Contact List</h1>

		<table>
			<thead>
				<tr>
					<th>Student ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Contact</th>

				</tr>
			</thead>

			<tbody>
		
			
				
				
				  <c:forEach var="admitted_student" items="${addmited_students}">
            <!-- Find the corresponding student from the enrolled list -->
            <c:set var="matchedStudent" value="${null}" />
            <c:forEach var="student" items="${students}">
                <c:if test="${student.enquiryId == admitted_student.enquiryId}">
                    <c:set var="matchedStudent" value="${student}" />
                </c:if>
            </c:forEach>

            <!-- Display the matched student details only once -->
            <c:if test="${not empty matchedStudent}">
               
                <tr>
                    <td>${admitted_student.admissionId}</td>
                    <td>${matchedStudent.studentName}</td>
                    <td>${matchedStudent.studentEmail}</td>
                    <td>${matchedStudent.studentContact}</td>
                </tr>
                
            </c:if>
        </c:forEach>
				
			</tbody>


		</table>
		
		</div>
	</div>
</body>
</html>