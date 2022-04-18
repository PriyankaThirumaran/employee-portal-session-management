package com.company.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.Employee;
import com.company.domain.PlainTextPassword;
import com.company.exceptions.EmpEmailAlreadyExistsException;
import com.company.exceptions.EmpPhoneNoAlreadyExistsException;
import com.company.exceptions.EmpUsernameAlreadyExistsException;
import com.company.exceptions.InvalidPasswordException;
import com.company.exceptions.NoSuchEmployeeException;
import com.company.repository.EmployeeRepository;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//Fetching all employee details
	public List<Employee> fetchAllEmployee() {
		return employeeRepository.fetchAllEmployee();
	}
	
	//Fetching an employee
	public Employee fetchEmployee(int empId) throws NoSuchEmployeeException{
		Employee employee = employeeRepository.fetchEmployee(empId);
		if (employee == null) {
			throw new NoSuchEmployeeException("Employee with " + empId + " does not exist");	
		}
		else {
			return employee;
		}		
	}
		
	//Adding an employee
	public String createEmployee(Employee employee) throws EmpUsernameAlreadyExistsException, EmpEmailAlreadyExistsException, EmpPhoneNoAlreadyExistsException, InvalidPasswordException {
		Employee employeeByUsername = employeeRepository.findByUsername(employee.getUsername());
		Employee employeeByEmail = employeeRepository.findByEmail(employee.getEmailId());
		Employee employeeByPhoneNo = employeeRepository.findByPhoneNo(employee.getPhoneNo());
		if (employeeByUsername != null)
			throw new EmpUsernameAlreadyExistsException("Username already exists. Try a new username");
		else if (employeeByEmail != null)
			throw new EmpEmailAlreadyExistsException("Email-ID already exists. Try a new Email-ID");
		else if (employeeByPhoneNo != null)
			throw new EmpPhoneNoAlreadyExistsException("Phone number already exists. Try a new one");
		else {
			PlainTextPassword pw = new PlainTextPassword();
			String plainPassword = employee.getPassword();
			if (!(pw.isValid(plainPassword))) {
				throw new InvalidPasswordException(pw.getErrMessage());
			}
			else {
				employee.setPassword(BCrypt.hashpw(plainPassword, BCrypt.gensalt()));
				employeeRepository.saveAndFlush(employee);
				return "Employee with Employee Id "+employee.getEmpId()+ " added sucessfully";
			}
		}
	}
		
	//Updating an employee
	public String updateEmployee(int empId, Employee employee) throws NoSuchEmployeeException, EmpUsernameAlreadyExistsException, EmpEmailAlreadyExistsException, EmpPhoneNoAlreadyExistsException, InvalidPasswordException {
		Employee emp = employeeRepository.fetchEmployee(empId);
		Employee employeeByUsername = employeeRepository.findByUsername(employee.getUsername(), empId);
		Employee employeeByEmail = employeeRepository.findByEmail(employee.getEmailId(), empId);
		Employee employeeByPhoneNo = employeeRepository.findByPhoneNo(employee.getPhoneNo(), empId);
		if (emp == null) 
			throw new NoSuchEmployeeException("Employee with " + empId + " does not exist. Try adding the employee first");
		else if (employeeByUsername != null)
			throw new EmpUsernameAlreadyExistsException("Username already exists. Try a new username");
		else if (employeeByEmail != null)
			throw new EmpEmailAlreadyExistsException("Email-ID already exists. Try a new Email-ID");
		else if (employeeByPhoneNo != null)
			throw new EmpPhoneNoAlreadyExistsException("Phone number already exists. Try a new one");
		else {
			PlainTextPassword pw = new PlainTextPassword();
			String plainPassword = employee.getPassword();
			if (!(pw.isValid(plainPassword))) {
				throw new InvalidPasswordException(pw.getErrMessage());
			}
			else {
				employee.setPassword(BCrypt.hashpw(plainPassword, BCrypt.gensalt()));
				employeeRepository.saveAndFlush(employee);
				return  "Employee with Employee Id "+empId+ " updated sucessfully";
			}
		}
	}
		
	//Deleting an employee
	public String deleteEmployee(int empId) throws NoSuchEmployeeException {
		boolean notFound = true;
		String response = "Employee with " + empId + " does not exist";
		Employee employee = employeeRepository.fetchEmployee(empId);
		if (employee != null) {
			notFound = false;
			employeeRepository.delete(empId);
			response = "Employee with Employee Id "+empId+ " deleted sucessfully";
		}
		if (notFound)
			throw new NoSuchEmployeeException(response);
		return response;
	}
	
	
	//For session details and restriction of access
	//Fetching the empId of a particular username
	public int findEmpId(String username) {
		return employeeRepository.findEmpId(username);
	}
	
}
