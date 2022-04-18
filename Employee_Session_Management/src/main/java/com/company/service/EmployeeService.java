package com.company.service;

import java.util.List;

import com.company.domain.Employee;
import com.company.exceptions.EmpEmailAlreadyExistsException;
import com.company.exceptions.EmpPhoneNoAlreadyExistsException;
import com.company.exceptions.EmpUsernameAlreadyExistsException;
import com.company.exceptions.InvalidPasswordException;
import com.company.exceptions.NoSuchEmployeeException;

public interface EmployeeService {
	
	//Fetching all employee details
	public List<Employee> fetchAllEmployee();
	
	//Fetching an employee
	public Employee fetchEmployee(int empId) throws NoSuchEmployeeException;
	
	//Adding an employee
	public String createEmployee(Employee employee) throws EmpUsernameAlreadyExistsException, EmpEmailAlreadyExistsException, EmpPhoneNoAlreadyExistsException, InvalidPasswordException;
	
	//Updating an employee
	public String updateEmployee(int empId, Employee employee) throws NoSuchEmployeeException, EmpUsernameAlreadyExistsException, EmpEmailAlreadyExistsException, EmpPhoneNoAlreadyExistsException, InvalidPasswordException;
	
	//Deleting an employee
	public String deleteEmployee(int empId) throws NoSuchEmployeeException;
	
	
	//For session details and restriction of access
	//Fetching the empId of a particular username
	public int findEmpId(String username);

}
