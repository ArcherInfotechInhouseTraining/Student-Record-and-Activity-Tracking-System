<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Attendance</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
 <!--    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
            text-align: left;
        }

        form {
            margin: 20px 0;
        }

        button {
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
    </style> -->
</head>
<body>
    <h2>Student Attendance Management</h2>

    <!-- Form to Add Attendance -->
    <form action="${pageContext.request.contextPath}/AttendenceController" method="post">
    	<h3>Add Attendance</h3>
        <input type="hidden" name="action" value="add">
        <label for="student_id">Student ID:</label>
        <input type="text" id="student_id" name="student_id" required><br><br>

        <label for="attendence_date">Date:</label>
        <input type="date" id="attendence_date" name="attendence_date" required><br><br>

        <label for="attendence">Attendance:</label>
         <input type="number" id="attendence" name="attendence" step="0.01" max="100" required><br><br>

        <button type="submit">Submit</button>
    </form>

    <!-- Table to Display Attendance Records -->
    <h3>Attendance Records</h3>
    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>
    
     <table>
    <thead>
        <tr>
            <th>Student ID</th>
            <th>Attendance Date</th>
            <th>Attendance</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="attendence" items="${attendenceList}">
            <tr>
                <td>${attendence.studentId}</td>
                <td>${attendence.attendenceDate}</td>
                <td>${attendence.attendence}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
     
</body>
</html>
