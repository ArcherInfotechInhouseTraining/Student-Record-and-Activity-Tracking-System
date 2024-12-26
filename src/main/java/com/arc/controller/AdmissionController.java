package com.arc.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arc.model.AdmissionModel;
import com.arc.model.AdmissionServices;


@WebServlet("/AdmissionController")
public class AdmissionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	AdmissionServices admissionServices ;
	
	@Override
	public void init() throws ServletException {
		try {
			admissionServices = new AdmissionServices();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		System.out.println("action : "+action);
		
		try {
			switch(action == null ? "list" : action) {
			case "insert":
				admissionDetails(request,response);
				break;
			case "delete":
				deleteStudent(request,response);
				break;
			case "update":
				updateStudent(request,response);
				break;
			case "edit":
				showEditForm(request,response);
				break;
			default:
				admissionStudentList(request,response);
				break;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		admissionServices.deleteStudent(id);
		response.sendRedirect("AdmissionController");
	}

	private void admissionStudentList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<AdmissionModel> admissionList = admissionServices.displayAllStudent();
		request.setAttribute("admissionList", admissionList);
		request.getRequestDispatcher("views/displayadmission.jsp").forward(request, response);
		
	}

	private void admissionDetails(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		int enquiryId = Integer.parseInt(request.getParameter("enquiryId"));
		int schoolId = Integer.parseInt(request.getParameter("schoolId"));
		Date dob = Date.valueOf(request.getParameter("dob"));
		String address = request.getParameter("address");
		int marks = Integer.parseInt(request.getParameter("marks"));
		String mentor = request.getParameter("mentor");
		String className = request.getParameter("className");
		
		AdmissionModel admissionModel = new AdmissionModel(0,enquiryId,schoolId,dob,address,marks,mentor,className);
		admissionServices.insert(admissionModel);
		request.setAttribute("admissionModel", admissionModel);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
