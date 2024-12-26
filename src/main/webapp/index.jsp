<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>school activity  tracking system</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<header>
	<h1>Student Activity Tracking System</h1>
    </header>
    <main>
        <p>Use the options below to view, add, edit, or delete school records.</p>
        <a href="SchoolApp">View Schools</a>
        <a href="SchoolApp?action=new">Add New School</a>
        
		<a href="${pageContext.request.contextPath}/ClassController">View Class List</a>
        <a href="views/addclass.jsp">Add New Class</a>
		<a href="${pageContext.request.contextPath}/TeacherController">View Teacher List</a>
        
        <a href="views/sis.jsp">Student Information</a>
        <a href="views/admission.jsp">Admission</a>
        <a href="views/PymentPage.jsp">Payment</a>
        <a href="views/displaysubject.jsp">Subjects</a>
        <a href="views/studentreport.jsp">Student Report</a>
        <a href="views/attendence.jsp">Attendence</a>
        
    </main>
</body>
</html>