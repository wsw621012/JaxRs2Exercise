package com.trend.frs.lucifer.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
	
	private String name;
	private String email;
	
	public Customer(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
}
