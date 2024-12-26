package com.arc.model;

public class ClassModel {

	private int classId;
	private int schoolId;
    private String className;
    private int classFees;
    
    public ClassModel() {
    	
    }
	public ClassModel(int classId, int schoolId, String className, int classFees) {
		
		this.classId = classId;
		this.schoolId = schoolId;
		this.className = className;
		this.classFees = classFees;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getClassFees() {
		return classFees;
	}
	public void setClassFees(int classFees) {
		this.classFees = classFees;
	}
    
}
