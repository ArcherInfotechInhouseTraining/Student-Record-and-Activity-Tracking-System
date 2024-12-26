package com.arc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arc.dbutil.DBConnect;

public class SubjectServices {

	Connection conn;
	
	public SubjectServices() throws SQLException {
		DBConnect dbConnect = DBConnect.getInstance();
		conn = dbConnect.getConnection();
	}
	
	private static final String INSERT_SUBJECT_SQL = "INSERT INTO subjects(sub_id,school_id, class_id, subject_name, marks_outoff) VALUES(?,?,?,?,?)";
	private static final String SELECT_ALL_SUBJECT = "SELECT * FROM subjects";	
	private static final String SELECT_SUBJECT_BY_ID = "SELECT school_id, class_id, subject_name, marks_outoff from subjects Where sub_id =?";
	private static final String UPDATE_SUBJECT ="UPDATE subjects SET subject_name=?, marks_outoff=? WHERE sub_id=?";
	
	public void insertSubject(SubjectModel subModel) throws SQLException {
		
		try(PreparedStatement preparedStatement = conn.prepareStatement(INSERT_SUBJECT_SQL)){
			preparedStatement.setInt(1, subModel.getSubId());
			preparedStatement.setInt(2, subModel.getClassId());
			preparedStatement.setString(3, subModel.getSubjectName());
			preparedStatement.setInt(4, subModel.getMarks());
			preparedStatement.executeUpdate();
		}
	}

	public List<SubjectModel> selectAllSubjects() throws SQLException {
		List<SubjectModel> subjectList = new ArrayList<>();
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_SUBJECT)){
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int subId = rs.getInt("sub_id");
				int schoolId = rs.getInt("school_id");
				int classId = rs.getInt("class_id");
				String subjectName = rs.getString("subject_name");
				int marks = rs.getInt("marks_outoff");
				
				subjectList.add(new SubjectModel(subId,schoolId, classId, subjectName, marks));
			}
		}
		return subjectList;
	}
	
	public SubjectModel selectSubjectByID(int subjectId) throws SQLException
	{
		SubjectModel subjectmodel = null;
		try(PreparedStatement ptmt = conn.prepareStatement(SELECT_SUBJECT_BY_ID))
		{
			ptmt.setInt(1, subjectId);
			ResultSet resultSet = ptmt.executeQuery();
			if(resultSet.next())
			{
//				  int subjectID = resultSet.getInt("sub_id");
				  int schoolID = resultSet.getInt("school_id");
				  int classID = resultSet.getInt("class_id");
				  String subjectName = resultSet.getString("subject_name");
				  int MarksOutOff = resultSet.getInt("marks_outoff");
				  
				  subjectmodel = new SubjectModel(subjectId,schoolID,classID,subjectName,MarksOutOff);
			}
		}
		return subjectmodel;
	}

	public void updateSubjectForm(SubjectModel subjectModel) throws SQLException {
		
		try(PreparedStatement ptmt = conn.prepareStatement(UPDATE_SUBJECT)){
			ptmt.setString(1, subjectModel.getSubjectName());
			ptmt.setInt(2, subjectModel.getMarks());
			ptmt.setInt(3, subjectModel.getSubId());
			ptmt.executeUpdate();
		}
		
	}
	
	
}
