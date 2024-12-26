package com.arc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.arc.dbutil.DBConnect;

public class AdmissionServices {

	Connection conn;
	
	public AdmissionServices() throws SQLException {
		DBConnect dbConnect = DBConnect.getInstance();
		conn = dbConnect.getConnection();
	}
	
	private static final String INSERT_ADMISSION_SQL = "INSERT INTO student_admission(enquiry_id, school_id, dob, address, marks, mentor, class_name ) VALUES(?,?,?,?,?,?,?)";
	private static final String SELECT_ALLSTUDENT_SQL = "SELECT * FROM student_admission WHERE admission_status=1";
	private static final String DELETE_STUDENT_SQL = "UPDATE student_admission SET admission_status=? WHERE admission_id=?";
	private static final String SELECT_SELECT_ADDMITIONID_SQL = "SELECT admission_id FROM student_admission WHERE enquiry_id=?";
	
//	public void insert(AdmissionModel admissionModel) throws SQLException {
//		
//		try(PreparedStatement prepareStatement = conn.prepareStatement(INSERT_ADMISSION_SQL)){
//			
//			prepareStatement.setInt(1, admissionModel.getEnquiryId());
//			prepareStatement.setInt(2, admissionModel.getSchoolId());
//			prepareStatement.setDate(3, (Date) admissionModel.getDob());
//			prepareStatement.setString(4, admissionModel.getAddress());
//			prepareStatement.setInt(5, admissionModel.getMarks());
//			prepareStatement.setString(6, admissionModel.getMentor());
//			prepareStatement.setString(7, admissionModel.getClass_name());
//			
//			prepareStatement.executeUpdate();
////			insertUser(admissionModel.getSchoolId(),);
//		}
//	}
	
	public void insert(AdmissionModel admissionModel) throws SQLException {
		Integer  enquiryId=0;
		Integer  addmitionId = 0;
		Integer  totalFee=0;
		
		EnrollmentServices enrollmentServices = new EnrollmentServices();
		try(PreparedStatement prepareStatement = conn.prepareStatement(INSERT_ADMISSION_SQL)){
			
			prepareStatement.setInt(1, admissionModel.getEnquiryId());
			prepareStatement.setInt(2, admissionModel.getSchoolId());
			prepareStatement.setDate(3, (Date) admissionModel.getDob());
			prepareStatement.setString(4, admissionModel.getAddress());
			prepareStatement.setInt(5, admissionModel.getMarks());
			prepareStatement.setString(6, admissionModel.getMentor());
			prepareStatement.setString(7, admissionModel.getClass_name());
			enquiryId=admissionModel.getEnquiryId();
			prepareStatement.executeUpdate();
			
			enrollmentServices.setScoolId(admissionModel.getSchoolId(),admissionModel.getEnquiryId());
			
//			 public void insertUser(int school_id,int student_id,int total_fee) throws SQLException {
			
			
		}
		try(PreparedStatement prepareStatement = conn.prepareStatement(SELECT_SELECT_ADDMITIONID_SQL)){
			prepareStatement.setInt(1, enquiryId);	
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next()) {
				addmitionId=rs.getInt(1);
				
			}
			
			
		}
		
		if(admissionModel.getClass_name().equals("FY")) {
			totalFee=50000;
			
		}else if(admissionModel.getClass_name().equals("SY")) {
			totalFee=750000;
		}else if(admissionModel.getClass_name().equals("TY")) {
			totalFee=100000;
		}else if(admissionModel.getClass_name().equals("BE")) {
			totalFee=100000;
		}
		
		PymentServices paymentServices = new PymentServices();
		paymentServices.insertUser( admissionModel.getSchoolId(), addmitionId, totalFee);
		
	}
	
	public java.util.List<AdmissionModel> displayAllStudent() throws SQLException {
		
		java.util.List<AdmissionModel> admissionModel = new ArrayList<>();
		
		try( PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALLSTUDENT_SQL) ){
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int admissionId = rs.getInt(1);
				int enquiryId = rs.getInt(2);
				int schoolId = rs.getInt(3);
				Date dob = rs.getDate(4);
				String address = rs.getString(5);
				int marks = rs.getInt(6);
				String mentor = rs.getString(7);
				String className = rs.getString(8);
				
				admissionModel.add(new AdmissionModel(admissionId,enquiryId,schoolId,dob,address,marks,mentor,className));
			}
		}
		return admissionModel;
	}
	public boolean deleteStudent(int id) throws Exception {
		try( PreparedStatement preparedStatement = conn.prepareStatement(DELETE_STUDENT_SQL)){
			preparedStatement.setInt(1, 0);
			preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() > 0;
		}
	}
}
