package com.demo.app.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "person")
public class Person extends Entity {

	private static final long serialVersionUID = 5376810403351001287L;

	private String name;
	private String address;
	private String email;
	private String company;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
