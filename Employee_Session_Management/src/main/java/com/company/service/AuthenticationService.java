package com.company.service;

public interface AuthenticationService {
	
	//validating login credentials
	public boolean validateCredentials(String username, String password);

}
