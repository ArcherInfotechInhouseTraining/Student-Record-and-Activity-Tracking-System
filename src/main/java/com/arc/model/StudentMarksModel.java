package com.arc.model;

public class StudentMarksModel {

	private int admissionId;
	private int subjectId;
	private int classId;
	private int marks;
	
	public StudentMarksModel() {
		
	}
	
	public StudentMarksModel(int admissionId, int subjectId, int classId, int marks) {
		super();
		this.admissionId = admissionId;
		this.subjectId = subjectId;
		this.classId = classId;
		this.marks = marks;
	}
	public int getAdmissionId() {
		return admissionId;
	}
	public void setAdmissionId(int admissionId) {
		this.admissionId = admissionId;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	
}
