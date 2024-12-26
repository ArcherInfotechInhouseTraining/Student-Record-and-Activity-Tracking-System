package com.arc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import com.arc.dbutil.DBConnect;
import com.mysql.cj.jdbc.PreparedStatementWrapper;

public class StudentMarksServices {

	Connection conn;
	
	public StudentMarksServices() throws SQLException {
		DBConnect dbConnect = DBConnect.getInstance();
		conn = dbConnect.getConnection();
	}
	
	private static final String INSERT_SUBJECT_SQL = "INSERT INTO students_marks(student_id,class_id,subject_id,marks_obtained) VALUES(?,?,?,?)";



	public void insertStudentMarks(int studentId, int subjectId,int classId, int marks) throws SQLException {
		
		try(PreparedStatement preparedStatement = conn.prepareStatement(INSERT_SUBJECT_SQL)){
			preparedStatement.setInt(1, studentId);
			preparedStatement.setInt(2, classId);
			preparedStatement.setInt(3, subjectId);
			preparedStatement.setInt(4, marks);
			preparedStatement.executeUpdate();
		}
	}
	
	
	
}
