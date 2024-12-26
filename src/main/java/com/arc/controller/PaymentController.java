package com.arc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arc.model.AdmissionServices;
import com.arc.model.ClassModel;
import com.arc.model.ClassServices;
import com.arc.model.PaymentModel;
import com.arc.model.PymentServices;
import com.arc.model.SchoolModel;
import com.arc.model.SchoolServices;
import com.arc.model.*;

/**
 * Servlet implementation class PaymentController
 */
@WebServlet("/PaymentController")
public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String selected_school;
	String Slected_Year;
	PymentServices paymentService;
	int admitionId;
	int amount;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public void init() {
		try {
			paymentService = new PymentServices();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");

		selected_school = request.getParameter("class");
		Slected_Year = request.getParameter("year");
//		admitionId= Integer.parseInt( request.getParameter("admitionId"));

		String studentId = request.getParameter("studentId");

		String amount = request.getParameter("amount");

		if ("add".equals(operation)) {
			request.setAttribute("selectedSchool", selected_school);
			request.setAttribute("selectedYear", Slected_Year);
			
			request.getSession().setAttribute("selectedSchool", selected_school);
		    request.getSession().setAttribute("selectedYear", Slected_Year);
		    
		    response.sendRedirect(request.getContextPath() + "/views/PymentPage.jsp");		
		    }

		if (operation != null && operation.equals("update")) {
		    System.out.println("Student ID: " + studentId);
		    System.out.println("Amount: " + amount);

		    try {
		        paymentService.updatePayment(Integer.parseInt(studentId), Integer.parseInt(amount));
		        request.setAttribute("selectedSchool", selected_school);
		        request.setAttribute("selectedYear", Slected_Year);
		        response.sendRedirect("views/PymentPage.jsp");
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

		/*
		 * System.out.println(selected_school); System.out.println(Slected_Year);
		 * 
		 * 
		 * String studentId = request.getParameter("studentId"); if(studentId!=null) {
		 * 
		 * }
		 */

	}

	public void listPayments(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		SchoolServices s = new SchoolServices();
		ClassServices c = new ClassServices();
		AdmissionServices a = new AdmissionServices();
		List<SchoolModel> schools = s.selectAllSchools();
		List<ClassModel> classes = c.selectAllClass();
		List<AdmissionModel> addmitions;

		/*
		 * request.setAttribute("schools", schools);
		 * request.getRequestDispatcher("views/PaymentPage.jsp").forward(request,
		 * response);
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
