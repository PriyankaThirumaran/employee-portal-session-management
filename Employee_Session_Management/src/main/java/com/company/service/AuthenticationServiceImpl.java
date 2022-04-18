package com.company.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.Employee;
import com.company.repository.EmployeeRepository;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//validating login credentials
	public boolean validateCredentials(String username, String password) {
		Employee emp = employeeRepository.findByUsername(username);
		String plainPassword = password;
		String hashedPassword = emp.getPassword();
		if (BCrypt.checkpw(plainPassword, hashedPassword))
			return true;
		else
			return false;
	}

}
