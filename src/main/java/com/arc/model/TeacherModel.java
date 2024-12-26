package com.arc.model;

public class TeacherModel {

	private int teacherId;
	private int schoolId;
	private String teacherName;
	private String teacherSubject;
	private String teacherEmail;
	private String teacherContact;
	
	
	
	public TeacherModel() {}

	public TeacherModel(int teacherId, int schoolId, String teacherName, String teacherSubject, String teacherEmail,
			String teacherContact) {
		this.teacherId = teacherId;
		this.schoolId = schoolId;
		this.teacherName = teacherName;
		this.teacherSubject = teacherSubject;
		this.teacherEmail = teacherEmail;
		this.teacherContact = teacherContact;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherSubject() {
		return teacherSubject;
	}

	public void setTeacherSubject(String teacherSubject) {
		this.teacherSubject = teacherSubject;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public String getTeacherContact() {
		return teacherContact;
	}

	public void setTeacherContact(String teacherContact) {
		this.teacherContact = teacherContact;
	}	
}
