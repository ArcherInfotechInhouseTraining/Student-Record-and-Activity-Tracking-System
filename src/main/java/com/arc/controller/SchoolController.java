package com.arc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arc.model.SchoolModel;
import com.arc.model.SchoolServices;

@WebServlet("/SchoolApp")
public class SchoolController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	SchoolServices schoolService;

	public void init() {
		try {
			schoolService = new SchoolServices();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("Action: " + action);
		try {
			switch (action == null ? "list" : action) {
			case "new":
				showNewForm(request, response);
				break;
			case "insert":
				insertSchool(request, response);
				break;
			case "delete":
				deleteSchool(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				updateSchool(request, response);
				break;
			default:
				listSchools(request, response);
				break;
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void listSchools(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SchoolModel> schools = schoolService.selectAllSchools();
		request.setAttribute("schools", schools);
		request.getRequestDispatcher("views/addschool.jsp").forward(request, response);
	}


	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("views/addschool.jsp").forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		SchoolModel school = schoolService.selectSchool(id);
		request.setAttribute("school", school);
		request.getRequestDispatcher("views/addschool.jsp").forward(request, response);
	}

	private void insertSchool(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");


		SchoolModel newSchool = new SchoolModel(0, name);
		schoolService.insertSchool(newSchool);
		response.sendRedirect("SchoolApp");
	}

	private void updateSchool(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");


		SchoolModel updatedSchool = new SchoolModel(id, name);
		schoolService.updateSchool(updatedSchool);
		response.sendRedirect("SchoolApp");
	}


	private void deleteSchool(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		schoolService.deleteSchool(id);
		response.sendRedirect("SchoolApp");
	}
}
