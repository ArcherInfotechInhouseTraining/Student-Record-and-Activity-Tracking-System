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
import com.arc.model.SubjectModel;
import com.arc.model.SubjectServices;


@WebServlet("/SubjectController")
public class SubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SubjectServices subService;
	
	@Override
	public void init() throws ServletException {
		try {
			subService = new SubjectServices();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		System.out.println("action"+action);
		
		try {
			switch( action == null ? "list" : action ) {
				
			case "insert":
				insertSubject(request,response);
				break;
				
			case "edit":
				showEditSubjectForm(request,response);
				break;
				
			case "update":
				updateSubject(request,response);
				break;
				
			case "delete":
				deleteSubject(request,response);
				break;
//			case "list":
//				subjectList(request,response);
//				break;
				
			default:
				subjectList(request,response);
				break;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	private void showEditSubjectForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

		int subjectId = Integer.parseInt(request.getParameter("sub_id"));
		SubjectModel subjectModel = subService.selectSubjectByID(subjectId);
		request.setAttribute("subjectModel", subjectModel);
		request.getRequestDispatcher("views/addsubject.jsp").forward(request, response);
	}

	private void deleteSubject(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void updateSubject(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		// TODO Auto-generated method stub
		int subId =Integer.parseInt(request.getParameter("subjectId"));
		int schoolId = Integer.parseInt(request.getParameter("schoollist"));
		int classId = Integer.parseInt(request.getParameter("classId"));
		String subjectName = request.getParameter("subjectName");
		int marks = Integer.parseInt(request.getParameter("subjectMark"));
	
		SubjectModel subjectModel = new SubjectModel(subId,schoolId,classId,subjectName,marks);
		subService.updateSubjectForm(subjectModel);
		response.sendRedirect("SubjectController");
	}

	private void insertSubject(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
		
		int schoolId = Integer.parseInt(request.getParameter("schoollist"));
		String subjectName = request.getParameter("subjectName");
		int classId = Integer.parseInt(request.getParameter("classId"));
		int subjectMarks = Integer.parseInt(request.getParameter("subjectMark"));
		
		
		SubjectModel subModel = new SubjectModel(0,schoolId, classId, subjectName, subjectMarks);
		subService.insertSubject(subModel);
		response.sendRedirect("SubjectController");
	}

	private void subjectList(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception, IOException {
		List<SubjectModel> subjectList = subService.selectAllSubjects();
		request.setAttribute("subjectList", subjectList);
		request.getRequestDispatcher("views/displaysubject.jsp").forward(request, response);
	}

}
