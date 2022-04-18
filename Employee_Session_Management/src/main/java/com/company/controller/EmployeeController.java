package com.company.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.Employee;
import com.company.domain.ErrorMessage;
import com.company.domain.SessionDetails;
import com.company.exceptions.AccessDeniedException;
import com.company.exceptions.EmpEmailAlreadyExistsException;
import com.company.exceptions.EmpPhoneNoAlreadyExistsException;
import com.company.exceptions.EmpUsernameAlreadyExistsException;
import com.company.exceptions.InvalidPasswordException;
import com.company.exceptions.NoSuchEmployeeException;
import com.company.exceptions.PleaseLoginException;
import com.company.service.AddressServiceImpl;
import com.company.service.EmployeeServiceImpl;
import com.company.service.SessionDetailsServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@Autowired
	private AddressServiceImpl addressService;
	
	@Autowired
	private SessionDetailsServiceImpl sessionDetailsService;
	
	//Fetching all employee details
	@GetMapping(value="/get/all", produces="application/json")
	public List<Employee> fetchAllEmployee(){
		return employeeService.fetchAllEmployee();
	}
	
	//Fetching an Employee detail
	@GetMapping(value="/get/{empId}", produces="application/json")
	public Employee fetchEmployee(HttpServletRequest request, @PathVariable int empId) throws NoSuchEmployeeException, AccessDeniedException {
		if (request.getSession(false) == null) 
			throw new PleaseLoginException("Please login to view your details");
		else {
			HttpSession session = request.getSession();
			if (empId == (int) session.getAttribute("empId")) 
				return employeeService.fetchEmployee(empId);
			else
				throw new AccessDeniedException("You have no access to other employee details");
		}
		
	}
	
	//Adding an Employee
	@PostMapping(value="/add", consumes="application/json")
	public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee, Errors errors) throws EmpUsernameAlreadyExistsException, EmpEmailAlreadyExistsException, EmpPhoneNoAlreadyExistsException, InvalidPasswordException{
		String response = "";
		if (errors.hasErrors()) {
			response = errors.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
			ErrorMessage error = new ErrorMessage();
			error.setErrorCode(HttpStatus.NOT_ACCEPTABLE.value());
			error.setMessage(response);
			return ResponseEntity.ok(error);
		}
		response = employeeService.createEmployee(employee);
		return ResponseEntity.ok().header("New Employee Added", "Successful").body(response);
	}
	
	//Updating an Employee
	@PutMapping(value="/update/{empId}", consumes="application/json")
	public ResponseEntity<?> updateEmployee(@PathVariable int empId, @Valid @RequestBody Employee employee, Errors errors) throws NoSuchEmployeeException, EmpUsernameAlreadyExistsException, EmpEmailAlreadyExistsException, EmpPhoneNoAlreadyExistsException, InvalidPasswordException {
		String response = "";
		if (errors.hasErrors()) {
			response = errors.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
			ErrorMessage error = new ErrorMessage();
			error.setErrorCode(HttpStatus.NOT_ACCEPTABLE.value());
			error.setMessage(response);
			return ResponseEntity.ok(error);
		}
		employee.setEmpId(empId);
		addressService.deleteAddress(empId);
		response = employeeService.updateEmployee(empId, employee);
		return ResponseEntity.ok(response);
	}
	
	//Deleting an Employee
	@DeleteMapping(value="/delete/{empId}")
	@ResponseBody
	public String deleteEmployee(@PathVariable int empId) throws NoSuchEmployeeException{
		return employeeService.deleteEmployee(empId);
	}
	
	//Fetching the session details
	@GetMapping(value="/get/session/{empId}", produces="application/json")
	public List<SessionDetails> fetchSession(HttpServletRequest request, @PathVariable int empId) throws NoSuchEmployeeException, AccessDeniedException {
		if (request.getSession(false) == null) 
			throw new PleaseLoginException("Please login to view your details");
		else {
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			if (empId == (int) session.getAttribute("empId")) 
				return sessionDetailsService.fetchSession(username);
			else
				throw new AccessDeniedException("You have no access to other employee session details");
		}	
	}
}
