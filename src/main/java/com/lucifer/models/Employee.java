package com.trend.frs.lucifer.models;

public class Employee {

	private String name;
	private String email;
	private int salary;
	
	public Employee(String name, String email, int salary) {
		this.name = name;
		this.email = email;
		this.salary = salary;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public int getSalary() {
		return salary;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
}
