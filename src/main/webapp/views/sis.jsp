<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@ page import="com.arc.model.AdmissionServices" %>
<%@ page import="com.arc.model.ClassServices" %>
<%@ page import="com.arc.model.SchoolServices" %>
<%@ page import="com.arc.model.AdmissionModel" %>
<%@ page import="com.arc.model.ClassModel" %>
<%@ page import="com.arc.model.SchoolModel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
    ClassServices classservice = new ClassServices();
    AdmissionServices admissionservice = new AdmissionServices();
    SchoolServices schoolService = new SchoolServices();

    List<ClassModel> classmodelList = classservice.selectAllClass();
    List<AdmissionModel> admissionModelList = admissionservice.displayAllStudent();
    List<SchoolModel> schoolModellist = schoolService.selectAllSchools();

    request.setAttribute("classmodel", classmodelList);
    request.setAttribute("admissionModelList", admissionModelList);
    request.setAttribute("schoolModellist", schoolModellist);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Information System</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <h2>Student Information System</h2>
    </header>
    <main>

        <table border="1">
            <thead>
                <tr>
                    <th>Class ID</th>
                    <th>Class Name</th>
                    <th>Class Fees</th>
                    <th>School Name</th>
                    <th>Student ID</th>
                    <th>Mentor Name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="classModel" items="${classmodel}">
                    <c:forEach var="admission" items="${admissionModelList}">
                        <c:if test="${admission.class_name == classModel.className && admission.schoolId == classModel.schoolId}">
                            <tr>
                                <td>${classModel.classId}</td>
                                <td>${classModel.className}</td>
                                <td>${classModel.classFees}</td>
                                <td>
                                    <c:forEach var="school" items="${schoolModellist}">
                                        <c:if test="${school.schoolId == admission.schoolId}">
                                            ${school.schoolName}
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>${admission.admissionId}</td>
                                <td>${admission.mentor}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </tbody>
        </table>
    </main>
</body>
</html>
