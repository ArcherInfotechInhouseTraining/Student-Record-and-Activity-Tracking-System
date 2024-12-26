package com.arc.model;

import java.util.Date;

public class AdmissionModel {
	
	private int admissionId;
	private int enquiryId;
	private int schoolId;
	private Date dob;
	private String address;
	private int marks;
	private String mentor;
	private String class_name;
	
	public AdmissionModel() {
		
	}
	
	
	public AdmissionModel(int admissionId, int enquiryId, int schoolId, Date dob, String address, int marks,
			String mentor, String class_name) {
		
		this.admissionId = admissionId;
		this.enquiryId = enquiryId;
		this.schoolId = schoolId;
		this.dob = dob;
		this.address = address;
		this.marks = marks;
		this.mentor = mentor;
		this.class_name = class_name;
	}
	public int getAdmissionId() {
		return admissionId;
	}
	public void setAdmissionId(int admissionId) {
		this.admissionId = admissionId;
	}
	public int getEnquiryId() {
		return enquiryId;
	}
	public void setEnquiryId(int enquiryId) {
		this.enquiryId = enquiryId;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public String getMentor() {
		return mentor;
	}
	public void setMentor(String mentor) {
		this.mentor = mentor;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

}
