package com.company.exceptions;

public class NoSuchEmployeeException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoSuchEmployeeException() {
		super();
	}
	
	public NoSuchEmployeeException(String errors) {
		super(errors);
	}

}
