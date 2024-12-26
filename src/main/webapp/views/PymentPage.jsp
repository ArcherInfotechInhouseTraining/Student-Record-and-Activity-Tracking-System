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
SchoolServices schoolServices = new SchoolServices();
List<SchoolModel> schools = schoolServices.selectAllSchools();
request.setAttribute("schools", schools);
%>

<%
ClassServices classServices = new ClassServices();
List<ClassModel> classes = classServices.selectAllClass();
request.setAttribute("classes", classes);
%>

<%
AdmissionServices admitionServices = new AdmissionServices();
List<AdmissionModel> admitionModel = admitionServices.displayAllStudent();
request.setAttribute("admitionModel", admitionModel);
%>
<%
EnrollmentServices enrollmentServices = new EnrollmentServices();
List<EnrollmentModel> enroll = enrollmentServices.selectStudent();
request.setAttribute("enroll", enroll);
%>

<%
PymentServices paymentServices = new PymentServices();
List<PaymentModel> payments = paymentServices.selectPaymentDetails();
request.setAttribute("payments", payments);
%>
<c:set var="selectedSchool" value="${sessionScope.selectedSchool}" />
<c:set var="selectedYear" value="${sessionScope.selectedYear}" />

<!DOCTYPE html>
<html>
<head>
<title>Class and Year Selection</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
}

label {
	font-weight: bold;
}

select {
	margin: 10px 0;
	padding: 5px;
	width: 200px;
}

input[type="submit"] {
	padding: 7px 15px;
	font-size: 16px;
	background-color: #4CAF50;
	color: white;
	border: none;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #45a049;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
	border-radius: 5px;
	overflow: hidden;
}

table, th, td {
	border: 1px solid #ddd;
}

th, td {
	padding: 12px;
	text-align: center;
	font-size: 14px;
}

th {
	background-color: #4CAF50;
	color: white;
	font-size: 16px;
}

tbody tr:nth-child(even) {
	background-color: #f9f9f9;
}

tbody tr:hover {
	background-color: #f1f1f1;
}

.pay-now-button {
	display: inline-block;
	padding: 10px 20px;
	font-size: 16px;
	color: white;
	background-color: #4CAF50;
	text-align: center;
	text-decoration: none;
	border-radius: 5px;
	border: none;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.pay-now-button:hover {
	background-color: #45a049;
}
</style>
</head>
<body>
	<h1>Select Class and Year</h1>

	<form action="../PaymentController" method="post">


		<label for="class">Choose a School:</label>&emsp; <select id="class"
			name="class">
			<option value="select">--Select School--</option>

			<c:forEach var="school" items="${schools}">
				<option value="${school.schoolName}">${school.schoolName}</option>
			</c:forEach>

			<!---------------------------------------------------------------------------------------------------------------------------------  -->
		</select> &nbsp; &nbsp; &nbsp; &nbsp;<label for="year">Choose a Class:</label>&emsp;
		<select id="year" name="year">


			<option value="">--Select Class--</option>

			<c:forEach var="schools" items="${schools}">

				<c:forEach var="classes" items="${classes}">
					<c:if test="${classes.schoolId == schools.schoolId}">

						<option value="${classes.className}">${classes.className}</option>

					</c:if>

				</c:forEach>
			</c:forEach>
		</select>
		
		  <button type="submit" name="operation" value="add" name="operation" class="pay-now-button">View</button>
		  
		  </form>
	<script>
	function submitPayment(studentId, button) {
	    // Find the row containing the clicked button
	    const row = button.closest("tr");

	    // Extract the entered amount from the input field
	    const amountInput = row.querySelector("input[name='amount']");
	    const amount = amountInput ? amountInput.value.trim() : null;

	    // Validate the amount
	    if (!amount || isNaN(amount)) {
	        alert("Please enter a valid amount.");
	        return;
	    }

	    // Create a form element to submit the data
	    const form = document.createElement("form");
	    form.method = "post";
	    form.action = "../PaymentController";

	    // Add studentId as a hidden field
	    const studentField = document.createElement("input");
	    studentField.type = "hidden";
	    studentField.name = "studentId";
	    studentField.value = studentId;
	    form.appendChild(studentField);

	    // Add amount as a hidden field
	    const amountField = document.createElement("input");
	    amountField.type = "hidden";
	    amountField.name = "amount";
	    amountField.value = amount;
	    form.appendChild(amountField);

	    // Add operation as a hidden field
	    const operationField = document.createElement("input");
	    operationField.type = "hidden";
	    operationField.name = "operation";
	    operationField.value = "update";
	    form.appendChild(operationField);

	    // Append the form to the document and submit
	    document.body.appendChild(form);
	    form.submit();
	}
	</script>


<c:if test="${selectedSchool!=null && selectedYear !=null }">




	<table>
		<thead>
			<tr>

				<th>Student ID</th>
				<th>Name</th>
				<th>Total Fee</th>
				<th>Fee Paid</th>
				<th>Fee Remaining</th>
				<th>Amount</th>
				<th>Pay</th>

			</tr>
		</thead>
		<tbody>
			<!-- Placeholder rows; dynamic rows can be populated here -->

			<c:forEach var="admited_student" items="${admitionModel}">
				<c:forEach var="enroll_stuedent" items="${enroll}">

					<c:forEach var="payments" items="${payments}">

						<c:if
							test="${enroll_stuedent.enquiryId == admited_student.enquiryId}">
							<c:if test="${payments.paymentId == admited_student.enquiryId && admited_student.class_name==selectedYear}">
								<!--  <input type="hidden" style="display: none;" name="studentId" value="${ admited_student.admissionId}" /> -->
								<tr>
									<td>${admited_student.admissionId}</td>
									<td>${enroll_stuedent.studentName}</td>
									<td>${payments.total_fee}</td>
									<td>${payments.fee_paid}</td>
									<td>${payments.fee_remaining}</td>
									<td><input type="text" style="height: 20px" id="amount"
										name="amount" placeholder="Enter the amount" /></td>
									<td>
										<button type="submit" value="update" class="pay-now-button" onclick="submitPayment('${admited_student.admissionId}', this)"> Pay Now </button>
									</td>
								</tr>
							</c:if>
						</c:if>
					</c:forEach>
				</c:forEach>
			</c:forEach>
		</tbody>
	</table>
	
	<%request.setAttribute("payments", null); %>
	
	</c:if>
</body>
</html>
