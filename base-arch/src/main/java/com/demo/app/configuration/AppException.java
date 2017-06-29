package com.demo.app.configuration;

public class AppException extends Exception {

	private static final long serialVersionUID = 7781059836018578812L;


	public AppException() {
		super();
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

}
