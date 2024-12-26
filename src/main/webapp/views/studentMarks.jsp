<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page import="com.arc.model.ClassModel"  %>
<%@ page import="com.arc.model.ClassServices" %>
	
<%@ page import="com.arc.model.SchoolServices"%>
<%@ page import="com.arc.model.SchoolModel"%>

<%@ page import="com.arc.model.AdmissionServices"%>
<%@ page import="com.arc.model.AdmissionModel"%>

<%@ page import="com.arc.model.SubjectServices"%>
<%@ page import="com.arc.model.SubjectModel"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>


<%
	//for class name list
	ClassServices classservice = new ClassServices();
	List<ClassModel> classmodelList = classservice.selectAllClass();
	request.setAttribute("classmodel", classmodelList);

	SchoolServices schoolService = new SchoolServices();
	List<SchoolModel> schoolModellist = schoolService.selectAllSchools();
	request.setAttribute("schoolModellist", schoolModellist);
	System.out.println("School Service Size"+schoolModellist.size());
	
	AdmissionServices admissionservice = new AdmissionServices();
	List<AdmissionModel> admissionModelList = admissionservice.displayAllStudent();
	request.setAttribute("admissionModelList", admissionModelList);
	
	SubjectServices subjectservice = new SubjectServices();
	List<SubjectModel> subjectModelList = subjectservice.selectAllSubjects();
	request.setAttribute("subjectModelList", subjectModelList);

	
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>student marks</title>
    	<link rel="stylesheet" type="text/css" href="../css/style.css">
	</head>
	<body>
		
		<form action="studentMarks.jsp">
			<h1>Select Fields</h1>
			<label for="schoolname">Select School Name:</label>
            <select id="schoollist" name="schoollist">
            	<c:forEach var="schools" items="${schoolModellist}">
            		<option value="${schools.schoolId}">${schools.schoolName}</option>
            	</c:forEach>
            </select><br><br>
            
			<label>Select Class Name:</label>
			<select name="classSelection">
				<c:forEach var="cmodel" items="${classmodel}">
					<option value="${cmodel.classId},${cmodel.className }">${cmodel.className}</option>
				
				</c:forEach>
			</select><br><br>
			
			<button type="submit">Submit</button>
		</form>
		
		<%
		    String schoolIdParam = request.getParameter("schoollist");
	    	String classSelection = request.getParameter("classSelection");
	    	
			String classNameParam = request.getParameter("className");
			String classIdParam = request.getParameter("className");
		    int schid = 0; // Default value for id
		    int cid = 0;
		    String className = null;
		    if ( (schoolIdParam != null && !schoolIdParam.isEmpty()) && (classSelection != null && !classSelection.isEmpty())) {
		        try {
		        	schid = Integer.parseInt(schoolIdParam);
		            String[] classParts = classSelection.split("\\,");
		            cid = Integer.parseInt(classParts[0]); // Extract classId
		            classNameParam = classParts[1]; // Extract className
		            
		            request.setAttribute("schid", schid);
		            request.setAttribute("classNameParam", classNameParam);
		            request.setAttribute("cid", cid);
		        } catch (NumberFormatException e) {
		            // Handle invalid number input
		            out.println("<p style='color:red;'>Invalid student ID format. Please enter a valid number.</p>");
		        }
		    }
		%>
		
		<form action="../StudentMarksController">
		
			<h1>Enter Student Marks</h1>
			<input type="hidden" name="action" value="insert">
			<table border="1">
			
				<thead>
					<tr>
						<th>StudentId</th>
						<!-- to set subject name as heading -->
						<c:forEach var="subject" items="${subjectModelList}">
									<c:if test="${subject.schoolId==schid && subject.classId==cid }">
										<th>${subject.subjectName}</th>
									</c:if>
						</c:forEach>
					</tr>
					
				</thead>
				<tbody>
				<!-- to take input for student each subject marks-->
					<c:forEach var="std" items="${admissionModelList}">
							   <%-- <p>std.schoolId: ${std.schoolId}</p>
							    <p>std.className: ${std.class_name}</p>
							    <th>Student Id</th> --%>
						<c:if test="${std.schoolId==schid && std.class_name==classNameParam }">
							<tr>
								<td>${std.admissionId}</td>
								<!-- it dynamically add one by one subject marks into marks table withe ther subjectId,admissionId and classId  -->
								<c:forEach var="subject" items="${subjectModelList}">
									<c:if test="${subject.schoolId==schid && subject.classId==cid }">
										<td><input type="text" name="marks[${std.admissionId}][${subject.subId}][${subject.classId}]"></td>
									</c:if>
								</c:forEach>
						    </tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			<button type="submit">Add Marks</button>
		</form>
		
	</body>
</html>