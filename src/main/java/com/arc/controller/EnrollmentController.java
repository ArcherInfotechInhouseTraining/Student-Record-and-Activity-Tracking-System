package com.arc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arc.model.EnrollmentModel;
import com.arc.model.EnrollmentServices;

@WebServlet("/EnrollmentController")
public class EnrollmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	EnrollmentServices eServices;
	
	public void init() {
		try {
			eServices = new EnrollmentServices();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		System.out.println("action: "+action);
		
		try {
			switch (action) {
			case "insert":
				enrollStudent(request,response);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + action);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	private void enrollStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String name = request.getParameter("studentName");
		String email = request.getParameter("studentEmail");
		String contact = request.getParameter("studentContact");
//		String schoolId = request.getParameter("studentId");
		
		EnrollmentModel emodel = new EnrollmentModel(0,0, name, email, contact) ;
		eServices.enrollStudent(emodel);
		request.setAttribute("emodel", emodel);
		request.getRequestDispatcher("views/admission.jsp").forward(request, response);
	}

}
