package com.trend.frs.lucifer.models;

public class Student {

	private String name;
	private String stId;
	
	public Student(String name, String stId) {
		this.name = name;
		this.stId = stId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getStudentId() {
		return stId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStId(String stId) {
		this.stId = stId;
	}
	
}
