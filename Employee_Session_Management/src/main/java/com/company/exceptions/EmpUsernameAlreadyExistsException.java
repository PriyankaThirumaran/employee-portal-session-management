package com.company.exceptions;

public class EmpUsernameAlreadyExistsException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public EmpUsernameAlreadyExistsException() {
		super();
	}
	
	public EmpUsernameAlreadyExistsException(String errors) {
		super(errors);
	}

}
