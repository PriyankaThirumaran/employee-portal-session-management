package com.company.exceptions;

public class EmpPhoneNoAlreadyExistsException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public EmpPhoneNoAlreadyExistsException() {
		super();
	}
	
	public EmpPhoneNoAlreadyExistsException(String errors) {
		super(errors);
	}
}
