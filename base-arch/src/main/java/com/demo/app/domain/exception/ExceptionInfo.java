package com.demo.app.domain.exception;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.demo.app.domain.Entity;

@Document(collection = "ExceptionInfo")
public class ExceptionInfo extends Entity implements Serializable {

	private static final long serialVersionUID = 8752756945905434156L;
	private Date dateTime;
	private String username;
	private ExceptionType type;
	private String message;
	private String stack;

	public ExceptionInfo() {
		super();
	}

	@Override
	public String getId() {
		return null;
	}

	@Override
	public void setId(String id) {
		// No id will be used
	}

	public ExceptionInfo(Date dateTime, String username, ExceptionType type, String message, String stack) {
		super();
		this.dateTime = dateTime;
		this.username = username;
		this.type = type;
		this.message = message;
		this.stack = stack;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void Date(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

	public ExceptionType getType() {
		return type;
	}

	public void setType(ExceptionType type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void hideDetails() {
		this.stack = null;
		this.username = null;
		this.dateTime = null;
		this.type = null;
	}

}
