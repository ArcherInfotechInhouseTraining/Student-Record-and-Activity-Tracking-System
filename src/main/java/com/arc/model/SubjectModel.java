package com.arc.model;

public class SubjectModel {
	
	private int subId;
	private int schoolId;
	private int classId;
	private String subjectName;
	private int marks;
	
	public SubjectModel(int subId,int schoolId, int classId, String subjectName, int marks) {
	
		this.subId = subId;
		this.schoolId = schoolId;
		this.classId = classId;
		this.subjectName = subjectName;
		this.marks = marks;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public SubjectModel() {
		
	}

	public int getSubId() {
		return subId;
	}

	public void setSubId(int subId) {
		this.subId = subId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	
}
