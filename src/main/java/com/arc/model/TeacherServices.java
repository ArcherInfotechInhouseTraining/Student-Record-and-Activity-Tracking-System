package com.arc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arc.dbutil.DBConnect;

public class TeacherServices {
	
	Connection connection = null;
	
	public TeacherServices() throws SQLException
	{
		DBConnect dbconnect = DBConnect.getInstance();
		connection = dbconnect.getConnection();
		System.out.println("Connection SuccessFully......");
	}
	
	private static final String INSERT_TEACHER="INSERT INTO add_teacher(school_id,teacher_name,teacher_subject,teacher_email,teacher_contact)values(?,?,?,?,?)";
	private static final String SELECT_ALL_TEACHERS ="SELECT * FROM add_teacher";
	private static final String SELECT_TEACHER_BY_ID ="SELECT school_id,teacher_name,teacher_subject,teacher_email,teacher_contact from add_teacher WHERE teacher_id=?";
	private static final String UPDATE_TEACHER ="UPDATE add_teacher SET teacher_name=?,teacher_subject=?, teacher_email=?, teacher_contact=? WHERE teacher_id=?";
	private static final String DELETE_TEACHER_RECORD ="DELETE FROM add_teacher WHERE teacher_id=?";
	
	public void insetTeacher(TeacherModel teacherModel) throws SQLException {
		try(PreparedStatement ptmt = connection.prepareStatement(INSERT_TEACHER)){
			ptmt.setInt(1, teacherModel.getSchoolId());
			ptmt.setString(2, teacherModel.getTeacherName());
			ptmt.setString(3,teacherModel.getTeacherSubject());
			ptmt.setString(4, teacherModel.getTeacherEmail());
			ptmt.setString(5, teacherModel.getTeacherContact());
			ptmt.executeUpdate();
		}
	}
	
	public List<TeacherModel> selectAllTeachers() throws SQLException
	{
		List<TeacherModel> teacherModels = new ArrayList<>();
		
		try(PreparedStatement ptmt = connection.prepareStatement(SELECT_ALL_TEACHERS))
		{
			ResultSet resultSet = ptmt.executeQuery();
			while(resultSet.next())
			{
				int teacherId = resultSet.getInt("teacher_id");
				int schoolId = resultSet.getInt("school_id");
				String teacherName=resultSet.getString("teacher_name");
				String teacherSubject=resultSet.getString("teacher_subject");
				String teacherEmail = resultSet.getString("teacher_email");
				String teacherContact = resultSet.getString("teacher_contact");
				teacherModels.add(new TeacherModel(teacherId,schoolId,teacherName,teacherSubject,teacherEmail,teacherContact));
			}
		}
		return teacherModels;
	}
	
	public TeacherModel selectTeacherById(int teacherId) throws SQLException
	{
		TeacherModel teacherModel = null;
		try(PreparedStatement ptmt = connection.prepareStatement(SELECT_TEACHER_BY_ID)){
			ptmt.setInt(1, teacherId);
			ResultSet resultset = ptmt.executeQuery();
			
			if(resultset.next())
			{
				int schoolId = resultset.getInt("school_id");
				String teacherName=resultset.getString("teacher_name");
				String teacherSubject = resultset.getString("teacher_subject");
				String teacherEmail = resultset.getString("teacher_email");
				String teacherContact=resultset.getString("teacher_contact");
				
				teacherModel=new TeacherModel(teacherId,schoolId,teacherName,teacherSubject,teacherEmail,teacherContact);
			}
		}
		return teacherModel;
	}

	public boolean updateTeacherForm(TeacherModel teacherModel) throws SQLException {
		
		try(PreparedStatement ptmt = connection.prepareStatement(UPDATE_TEACHER))
		{
			ptmt.setString(1, teacherModel.getTeacherName());
			ptmt.setString(2, teacherModel.getTeacherSubject());
			ptmt.setString(3, teacherModel.getTeacherEmail());
			ptmt.setString(4, teacherModel.getTeacherContact());
			ptmt.setInt(5, teacherModel.getTeacherId());
			
			return ptmt.executeUpdate()>0;
		}
	}

	public boolean deleteRecordsOfTeacher(int teacherId) throws SQLException {
		
		try(PreparedStatement ptmt = connection.prepareStatement(DELETE_TEACHER_RECORD))
		{
			ptmt.setInt(1, teacherId);
			return ptmt.executeUpdate()>0;
		}	
	}
}
