package com.demo.app.configuration.exceptions;

public class AppException extends Exception implements AppExceptionInterface{

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
