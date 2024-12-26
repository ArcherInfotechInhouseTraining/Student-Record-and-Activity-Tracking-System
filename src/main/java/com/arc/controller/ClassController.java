package com.arc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arc.model.ClassModel;
import com.arc.model.ClassServices;


@WebServlet("/ClassController")
public class ClassController extends HttpServlet {
	
	ClassServices classservice;
	public void init()
	{
		try {
			classservice= new ClassServices();
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
				case "insertclass":
					insertClass(request,response);
					break;
					
				case "edit":
					showEditClassForm(request,response);
					break;
					
				case "update":
					updateClass(request,response);
					break;
					
				case "delete":
					deleteClass(request,response);
					break;
					
				default:
					listOfClass(request,response);
					break;
			}
		}catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void deleteClass(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		int classId = Integer.parseInt(request.getParameter("class_id"));
		classservice.deleteClassRecord(classId);
		response.sendRedirect("ClassController");
	}

	private void updateClass(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	
		int classId =Integer.parseInt(request.getParameter("classid"));
		String className =request.getParameter("class_name");
		int classFees =Integer.parseInt(request.getParameter("class_fees"));
		
		ClassModel classmodel = new ClassModel(classId,0,className,classFees);
		classservice.updateClassForm(classmodel);
		response.sendRedirect("ClassController");
		
	}

	private void showEditClassForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int classId = Integer.parseInt(request.getParameter("class_id"));
		ClassModel classmodel = classservice.selectClassByID(classId);
		request.setAttribute("classmodel", classmodel);
		request.getRequestDispatcher("views/addclass.jsp").forward(request, response);
	}

	private void listOfClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<ClassModel> classmodel = classservice.selectAllClass();
		request.setAttribute("classmodel", classmodel);
		request.getRequestDispatcher("views/classCRUD.jsp").forward(request, response);
	}

	private void insertClass(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		String className = request.getParameter("class_name");
		int classFees = Integer.parseInt(request.getParameter("class_fees"));
		ClassModel classmodel = new ClassModel(0,0,className,classFees);
		classservice.insertClass(classmodel);
		response.sendRedirect("ClassController");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}