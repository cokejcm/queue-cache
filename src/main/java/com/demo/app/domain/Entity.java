package com.demo.app.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.Id;

@MappedSuperclass
public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 5908519522358747038L;

	@Id
	private String id;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
