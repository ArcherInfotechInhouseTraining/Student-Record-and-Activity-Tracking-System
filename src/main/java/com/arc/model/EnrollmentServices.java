package com.arc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.arc.dbutil.DBConnect;

public class EnrollmentServices {

	Connection conn;
	EnrollmentModel enrollmentModel = new EnrollmentModel();
	int enquiryId = -1;
	
	public EnrollmentServices() throws SQLException {
		DBConnect dbConnect = DBConnect.getInstance();
		conn = dbConnect.getConnection();
	}
	
	private static final String INSERT_STUDENT_SQL = "INSERT INTO student_enquiry (school_id, student_name, student_email, student_contact) VALUES(?,?,?,?) ";
	private static final String SELECT_STUDENT_SQL = "SELECT * FROM student_enquiry";
	private static final String SET_SCHOOLID_SQL = "UPDATE student_enquiry SET school_id=? WHERE enquiry_id=?";
	
	public void enrollStudent( EnrollmentModel enrollmentModel) throws Exception {
		try( PreparedStatement preparedStatement = conn.prepareStatement(INSERT_STUDENT_SQL, Statement.RETURN_GENERATED_KEYS)){
			
			//Statement.RETURN_GENERATED_KEYS - it help to get an autogenrated id
			
			preparedStatement.setInt(1, enrollmentModel.getSchoolId());
			preparedStatement.setString(2, enrollmentModel.getStudentName());
			preparedStatement.setString(3, enrollmentModel.getStudentEmail());
			preparedStatement.setString(4, enrollmentModel.getStudentContact());
			int row = preparedStatement.executeUpdate();
			
			ResultSet rs = null;
			if(row > 0) {
				rs = preparedStatement.getGeneratedKeys();
				if(rs.next()) {
					enquiryId = rs.getInt(1);
				}
			}
			
			if( enquiryId != -1) {
				enrollmentModel.setEnquiryId(enquiryId);
			}
		}
	}
	
	public List<EnrollmentModel> displayEnrolledDetails() throws SQLException{
		
		java.util.List<EnrollmentModel> enrollmentModel = new ArrayList<>();
		
		try( PreparedStatement preparedStatement = conn.prepareStatement(SELECT_STUDENT_SQL) ){
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {

				int enquiryId = rs.getInt(1);
				int schoolId = rs.getInt(2);
				String studentName = rs.getString(3);
				String studentEmail = rs.getString(4);
				String studentContact = rs.getString(5);
				
				enrollmentModel.add(new EnrollmentModel(enquiryId,schoolId,studentName,studentEmail,studentContact));
			}
		}
		return enrollmentModel;
	}
	
	
	public List<EnrollmentModel> selectStudent() throws Exception {
		 
		 List<EnrollmentModel> enquired_students = new ArrayList<>();
		 
		 AdmissionServices admitionServices = new AdmissionServices();
		 List<AdmissionModel> addmited_students = admitionServices.displayAllStudent();
		 
		 
		 try (
	            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_STUDENT_SQL)) {
	            	ResultSet resultSet = preparedStatement.executeQuery();
	            while (resultSet.next()) {
	                int enquiry_id = resultSet.getInt("enquiry_id");
	                int school_id =  resultSet.getInt("school_id");
	           
	                String student_name = resultSet.getString("student_name");
	                String student_email = resultSet.getString("student_email");
	                String student_contact = resultSet.getString("student_contact");
	                
	                enquired_students.add(new EnrollmentModel(enquiry_id, school_id, student_name, student_email,student_contact));
	            }
	        }
	
		return enquired_students;
	}

	public void setScoolId(int schoolId, int enqueryId) throws SQLException {
		
		try(PreparedStatement ptmt = conn.prepareStatement(SET_SCHOOLID_SQL)){
			ptmt.setInt(1,schoolId);
			ptmt.setInt(2, enqueryId);
			ptmt.executeUpdate();
		}
	}
}
