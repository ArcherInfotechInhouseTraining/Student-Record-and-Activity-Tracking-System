<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="com.arc.model.*"%>
<%@ page import="com.arc.model.PaymentModel"%>
<%@ page import="java.util.*"%>
<%
/* Addmisssion Service */
AdmissionServices admissionServices = new AdmissionServices();
List<AdmissionModel> admissionModel = admissionServices.displayAllStudent();
/* if (admissionModel == null) {
    admissionModel = new ArrayList<>();
} */
request.setAttribute("admissionModel", admissionModel);

/* ------------------------------------------  */

/* Enrollment Services */
EnrollmentServices enrollmentService = new EnrollmentServices();
List<EnrollmentModel> enrollmentModel = enrollmentService.displayEnrolledDetails();
request.setAttribute("enrollmentModel", enrollmentModel);
System.out.println("EnrollMent Size" + enrollmentModel.size());

/* Payment Services */
PymentServices paymentService = new PymentServices();
List<PaymentModel> paymentModel = paymentService.selectPaymentDetails();
request.setAttribute("paymentModel", paymentModel);
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Student Profile</title>
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/studentreport.css"> 
</head>
<body>
	<form action="studentreport.jsp">
		<label for="name">Enter Student ID :</label> <br>
		<br> <input type="text" name="enquiry_id"
			placeholder="Enter Enquiry ID" required /> <br>
		<br> <input type="submit" value="submit">
	</form>
	<br>

	<!-- Scriplet To get The Submitted Id -->
	<%
	String studentIdParam = request.getParameter("enquiry_id");
	int enquiryId = 0;
	if (studentIdParam != null && !studentIdParam.isEmpty()) {
		try {
			enquiryId = Integer.parseInt(studentIdParam);
		} catch (NumberFormatException e) {
			enquiryId = 0; // Handle invalid input gracefully
		}
	}
	request.setAttribute("enquiryId", enquiryId);
	%>

	<!-- Display Student Details -->
	<div class="container">
		<c:choose>
			<c:when test="${StudentId != 0}">
				<c:set var="recordfound" value="false" />

				<!-- Admiision Model TO get Details -->
				<c:forEach var="admission" items="${admissionModel}">
					<c:if test="${enquiryId==admission.enquiryId}">
						<h2>Personal Details</h2>
						<!-- <div class="highlight-box"> -->
						<p>
							<strong>Student ID:</strong>${admission.enquiryId}</p>
						<p>
							<strong>Student DOB:</strong>${admission.dob}</p>
						<p>
							<strong>Student Address:</strong>${admission.address}</p>
						<c:set var="recordfound" value="true" />
					</c:if>
				</c:forEach>

				<!-- EnrollMentModel Details  -->
				<c:forEach var="enroll" items="${enrollmentModel}">
					<c:if test="${enquiryId==enroll.enquiryId}">
						<p>
							<strong>Student Name:</strong>${enroll.studentName}</p>
						<p>
							<strong>Student Email:</strong>${enroll.studentEmail}</p>
						<p>
							<strong>Student Contact:</strong>${enroll.studentContact}</p>
						<c:set var="recordfound" value="true" />
					</c:if>
				</c:forEach>

				<!-- AdmissionModel Used for Student Educational Details  -->
				<c:forEach var="education" items="${admissionModel}">
					<c:if test="${enquiryId==education.enquiryId}">
						<h2>Educational Details</h2>
						<p>
							<strong>Student Marks:</strong>${education.marks}</p>
						<p>
							<strong>Student Mentor:</strong>${education.mentor}</p>
						<p>
							<strong>Student ClassName:</strong>${education.class_name}</p>
						<!-- </div> -->
						<c:set var="recordfound" value="true" />
					</c:if>
				</c:forEach>
				<!--PaymentModel Used For Payments Details  -->
				<c:forEach var="paymet" items="${paymentModel}">
					<c:if test="${enquiryId==paymet.studentId}">
						<h2>Payment Details</h2>
						<p>
							<strong>Fees Paid:</strong>${paymet.fee_paid}</p>
						<p>
							<strong>Fees Remaining:</strong>${paymet.fee_remaining}</p>
						<p>
							<strong>Total Fees:</strong>${paymet.total_fee}</p>
						<c:set var="recordfound" value="true" />
					</c:if>
				</c:forEach>

				<c:if test="${recordfound==false}">
					<p>No Student Found With This Record</p>
				</c:if>
			</c:when>

			<c:otherwise>
				<p>Please enter a Student ID to search.</p>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>