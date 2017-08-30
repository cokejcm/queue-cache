package com.demo.app.domain.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import com.demo.app.domain.Entity;

@Document(collection = "ExceptionInfo")
public class ExceptionInfo extends Entity implements Serializable {

	private static final long serialVersionUID = 8752756945905434156L;
	private LocalDateTime dateTime;
	private String username;
	private ExceptionType type;
	private String message;
	private StackTraceElement[] stack;

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

	public ExceptionInfo(LocalDateTime dateTime, String username, ExceptionType type, String message, StackTraceElement[] stack) {
		super();
		this.dateTime = dateTime;
		this.username = username;
		this.type = type;
		this.message = message;
		this.stack = stack;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public StackTraceElement[] getStack() {
		return stack;
	}

	public void setStack(StackTraceElement[] stack) {
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
		if (!this.type.equals(ExceptionType.APP_EXCEPTION)) {
			this.message = "ERROR INTERNO";
		}
	}

}
