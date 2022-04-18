package com.company.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.company.domain.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	//Fetching all employee details
	@Query(value = "SELECT * FROM Employee", nativeQuery = true)
	public List<Employee> fetchAllEmployee();
		
	//Fetching an employee
	@Query(value= "SELECT * FROM Employee WHERE emp_id = ?1", nativeQuery = true)         //indexed query parameter
	public Employee fetchEmployee(int empId);
			
	//Adding an employee
	//saveAndFlush(S Entity)
			
	//Updating an employee
	// save and flush method
			
	//Deleting an employee
	//delete(empId) method
	
	//find by username
	@Query(value= "SELECT * FROM Employee WHERE username = ?1", nativeQuery = true)
	public Employee findByUsername(String username);
	
	//find by email
	@Query(value= "SELECT * FROM Employee WHERE email_id = ?1", nativeQuery = true)
	public Employee findByEmail(String email);
	
	//find by phone no
	@Query(value= "SELECT * FROM Employee WHERE phone_no = ?1", nativeQuery = true)
	public Employee findByPhoneNo(String phoneNo);
	
	//find by username
	@Query(value= "SELECT * FROM Employee WHERE username = :username AND emp_id != :empId", nativeQuery = true)
	public Employee findByUsername(String username, int empId);
		
	//find by email
	@Query(value= "SELECT * FROM Employee WHERE email_id = :email AND emp_id != :empId", nativeQuery = true)
	public Employee findByEmail(String email, int empId);
		
	//find by phone no
	@Query(value= "SELECT * FROM Employee WHERE phone_no = :phoneNo AND emp_id != :empId", nativeQuery = true)
	public Employee findByPhoneNo(String phoneNo, int empId);
	
	
	//For session details and restriction of access
	//Fetching the empId of a particular username
	@Query(value= "SELECT emp_id FROM Employee WHERE username = :username", nativeQuery = true)
	public int findEmpId(String username);
}











