package com.arc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arc.model.TeacherModel;
import com.arc.model.TeacherServices;

@WebServlet("/TeacherController")
public class TeacherController extends HttpServlet {
	
	TeacherServices teacherService;
	
	public void init() {
		try {
			teacherService = new TeacherServices();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		
		try
		{
			switch( action==null ? "list":action)
			{
				case "insertteacher":
					insertTeacher(request,response);
					break;
					
				case "edit":
					showTeacherForm(request,response);
					break;
					
				case "updateteacher":
					updateTeacher(request,response);
					break;
					
				case "delete":
					deleteTeacher(request,response);
					break;
					
				default:
					listOfTeachers(request,response);
					break;
			}
		}catch(Exception e)
		{
			throw new ServletException(e);
		}
	}
	
	private void deleteTeacher(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int teacherId = Integer.parseInt(request.getParameter("teacher_id"));
		teacherService.deleteRecordsOfTeacher(teacherId);
		response.sendRedirect("TeacherController");
	}

	private void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
	
		int teacherId =Integer.parseInt(request.getParameter("teacherid"));
		String teacherName = request.getParameter("teacher_name");
		String teacherSubject = request.getParameter("teacher_subject");
		String teacherEmail = request.getParameter("teacher_email");
		String teacherContact = request.getParameter("teacher_contact");
		
		TeacherModel teacherModel = new TeacherModel(teacherId,0,teacherName,teacherSubject,teacherEmail,teacherContact);
		teacherService.updateTeacherForm(teacherModel);
		response.sendRedirect("TeacherController");
	}

	private void showTeacherForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		int teacherId =Integer.parseInt(request.getParameter("teacher_id"));
		TeacherModel teachermodel = teacherService.selectTeacherById(teacherId);
		request.setAttribute("teachermodel", teachermodel);
		request.getRequestDispatcher("views/addteacher.jsp").forward(request, response);
	}

	private void listOfTeachers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<TeacherModel> teacherModel = teacherService.selectAllTeachers();
		request.setAttribute("teacherModel", teacherModel);
		request.getRequestDispatcher("views/teacher-list.jsp").forward(request, response);
	}

	private void insertTeacher(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		int SchoolId =Integer.parseInt(request.getParameter("schoollistforteacher"));
		String teacherName = request.getParameter("teacher_name");
		String teacherSubject = request.getParameter("teacher_subject");
		String teacherEmail= request.getParameter("teacher_email");
		String teacherContact= request.getParameter("teacher_contact");
		TeacherModel teacherModel = new TeacherModel(0,SchoolId,teacherName,teacherSubject,teacherEmail,teacherContact);
		teacherService.insetTeacher(teacherModel);
		response.sendRedirect("TeacherController");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
