<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>School records and activity System</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <h1>Add School</h1>
    </header>
    <main>
        <h2>School Form</h2>
        <form action="../SchoolApp" method="post">
            <!-- Action: Insert or Update -->
            <input type="hidden" name="action" value="${school == null ? 'insert' : 'update'}" />
            
            <!-- Hidden Field for ID (Update case) -->
            <c:if test="${school != null}">
                <input type="hidden" name="id" value="${school.schoolId}" />
            </c:if>

            <!-- School Name -->
            <label for="name">School Name:</label>
            <input type="text" id="name" name="name" value="${school != null ? school.schoolName : ''}" required />
            <br/><br/>


            <!-- Submit Button -->
            <button type="submit">Save</button>
        </form>
        <br/>
        <a href="index.jsp">Back to Home</a>
    </main>
    
    
    <header>
        <h2>School List</h2>
    </header>
    <main>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>School Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="school" items="${schools}">
                    <tr>
                        <td>${school.schoolId}</td>
                        <td>${school.schoolName}</td>
                        <td>
                            <a href="SchoolApp?action=edit&id=${school.schoolId}">Edit</a> |
                            <a href="SchoolApp?action=delete&id=${school.schoolId}">Delete</a> 
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

<!--         <a href="SchoolApp?action=new">Add New School</a> |
        <a href="index.jsp">Home</a> -->
    </main>
    
</body>
</html>
