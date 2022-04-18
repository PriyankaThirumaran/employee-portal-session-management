package com.company.exceptions;

public class AccessDeniedException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public AccessDeniedException() {
		super();
	}
	
	public AccessDeniedException(String errors) {
		super(errors);
	}
}
