package com.demo.app.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import com.demo.app.configuration.cache.Cacheable;

@Document(collection = "person")
@Cacheable
public class Person extends Entity {

	private static final long serialVersionUID = 5376810403351001287L;

	private String name;
	private String address;
	private String email;
	private String company;

	public Person(String id, String name, String address, String email, String company) {
		super();
		this.setId(id);
		this.name = name;
		this.address = address;
		this.email = email;
		this.company = company;
	}

	public Person() {
		super();
	}

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
