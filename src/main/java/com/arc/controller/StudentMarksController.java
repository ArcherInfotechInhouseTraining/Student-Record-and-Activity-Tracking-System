package com.arc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arc.model.StudentMarksServices;




@WebServlet("/StudentMarksController")
public class StudentMarksController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	StudentMarksServices studentService;
	
	@Override
	public void init() throws ServletException {
		try {
			studentService = new StudentMarksServices();
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
		
		if(action.equals("insert")) {
			try {
				insertMarks(request.getParameterMap());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	private void insertMarks(Map<String, String[]> parameterMap) throws Exception {
		
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();

            // Check if the key matches the "marks" pattern
            if (key.startsWith("marks[")) {
                // Parse studentId and subjectId from the key
                String[] ids = key.substring(6, key.length() - 1).split("\\]\\[");
                int admissionId = Integer.parseInt(ids[0]);
                int subjectId = Integer.parseInt(ids[1]);
                int classId = Integer.parseInt(ids[2]);

                // Parse marks value
                int marks = Integer.parseInt(entry.getValue()[0]);

                // Save to the database
                studentService.insertStudentMarks(admissionId, subjectId, classId, marks);
            }
        }
		
	}


//	private void insertMarks(HttpServletRequest request, HttpServletResponse response) {
//		
////		studentService.insertMarks(request.getParameterMap());
////		 Map<String, String[]> parameterMap = request.getParameterMap();
//		 
//
////		int schoolId = Integer.parseInt(request.getParameter("schoollist"));
////		String subjectName = request.getParameter("subjectName");
////		int classId = Integer.parseInt(request.getParameter("classId"));
////		int subjectMarks = Integer.parseInt(request.getParameter("subjectMark"));
////		
////		
////		SubjectModel subModel = new SubjectModel(0,schoolId, classId, subjectName, subjectMarks);
////		subService.insertSubject(subModel);
////		response.sendRedirect("SubjectController");
//	}

}
