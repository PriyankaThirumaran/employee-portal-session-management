package com.company.exceptions;

public class SessionTimedOutException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public SessionTimedOutException() {
		super();
	}
		
	public SessionTimedOutException(String errors) {
		super(errors);
	}

}
