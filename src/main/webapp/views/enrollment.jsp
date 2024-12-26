<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Enrollment Form</title>
    	<link rel="stylesheet" type="text/css" href="../css/style.css">
	</head>
	<body>
		<form action="../EnrollmentController" >
			<h1>Enrollment Form</h1>
			<%-- <input type="hidden" name="schoolId" value="${schools.getSchoolId() }"> --%>
			<input type="hidden" name="action" value="insert">
			<label>Enter Your Name</label>
			<input type="text" name="studentName" placeholder="Enter Your Name"><br><br>
			<label>Enter Your Email</label>
			<input type="email" name="studentEmail" placeholder="Enter Your Email"><br><br>
			<label>Enter Your Contact</label>
			<input type="text" name="studentContact" placeholder="Enter Your Contact"><br><br>
			<input type="submit" value="Enroll">
		</form>
	</body>
</html>