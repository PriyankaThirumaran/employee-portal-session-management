package com.company.exceptions;

public class PleaseLoginException extends RuntimeException{
		
	private static final long serialVersionUID = 1L;

	public PleaseLoginException() {
		super();
	}
		
	public PleaseLoginException(String errors) {
		super(errors);
	}

}


