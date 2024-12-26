package com.arc.model;

public class EnrollmentModel {
	
	private String studentName;
	private String studentEmail;
	private String studentContact;
	private int schoolId;
	private int enquiryId;
	
	
	public EnrollmentModel(int enquiryId,int schoolId,String studentName, String studentEmail, String studentContact) {

		this.enquiryId = enquiryId;
		this.schoolId = schoolId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.studentContact = studentContact;
	}
	public EnrollmentModel() {
		// TODO Auto-generated constructor stub
	}
	
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentContact() {
		return studentContact;
	}
	public void setStudentContact(String studentContact) {
		this.studentContact = studentContact;
	}
	public int getEnquiryId() {
		return enquiryId;
	}
	public void setEnquiryId(int enquiryId) {
		this.enquiryId = enquiryId;
	}
	
}
