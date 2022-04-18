package com.company.exceptions;

public class EmpEmailAlreadyExistsException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public EmpEmailAlreadyExistsException() {
		super();
	}
	
	public EmpEmailAlreadyExistsException(String errors) {
		super(errors);
	}

}