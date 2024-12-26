<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.arc.model.SchoolModel"%>
<%@ page import="com.arc.model.PaymentModel"%>
<%@ page import="com.arc.model.EnrollmentModel"%>
<%@ page import="com.arc.model.AdmissionModel"%>
<%@ page import="com.arc.model.ClassModel"%>
<%@ page import="com.arc.model.SchoolServices"%>
<%@ page import="com.arc.model.PymentServices"%>
<%@ page import="com.arc.model.EnrollmentServices"%>
<%@ page import="com.arc.model.AdmissionServices"%>
<%@ page import="com.arc.model.ClassServices"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
// Retrieve the student ID from the query parameter
/* String studentId = request.getParameter("student_id"); */
int studentId = Integer.parseInt(request.getParameter("student_id"));
request.setAttribute("studentId", studentId);
%>

 <%
 AdmissionServices admitionServices = new AdmissionServices();
List<AdmissionModel> admitionModel = admitionServices.displayAllStudent();
request.setAttribute("admitionModel", admitionModel);
%> 

<%
PymentServices paymentServices = new PymentServices();
List<PaymentModel> payments = paymentServices.selectPaymentDetails();
request.setAttribute("payments", payments);
%>

<%
EnrollmentServices enrollmentServices = new EnrollmentServices();
List<EnrollmentModel> enroll = enrollmentServices.selectStudent();
request.setAttribute("enroll", enroll);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pay Now Page</title>
</head>
<style>
* {
	margin: 0;
	padding: 0;
}

.container {
	width: 100vw;
	height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
}

.box {
	border: 2px solid red;
}
</style>

<body>
	<div class="container">

		<div class="box">


			<h1>Payment Page</h1>

			<!-- Display the studentId -->
			<p>Student ID: ${studentId}</p>
			<%-- <c:forEach var="payment" items="${payments}">
				<c:forEach var="enroll" items="${enroll}">
					 <c:if test="${enroll.schoolId == schools.schoolId}">
					
					
					<p>Payment Student ID: ${payment.studentId}</p>
					
					   </c:if>
				</c:forEach>
			</c:forEach> --%>
			
			<c:forEach var="admitionModel" items="${admitionModel}">
			 <c:if test="${ addmtionModel.admissionId == studentId}">
					<p>${ addmtionModel.admissionId}</p>
			  </c:if>
			</c:forEach>
			

			<!-- <input type="text" > -->

		</div>
	</div>

</body>
</html>
