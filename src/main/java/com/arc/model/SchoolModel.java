package com.arc.model;

public class SchoolModel {
	private int schoolId;
	private String schoolName;

	public SchoolModel(int id, String name) {
		this.schoolId = id;
		this.schoolName = name;

	}

	// Getters and Setters
	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int id) {
		this.schoolId = id;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String name) {
		this.schoolName = name;
	}

}
