package com.company.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Employee")
public class Employee implements Serializable{
	
	static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private int empId;
	
	@Column(name = "emp_name")
	@NotBlank(message = "Employee name is mandatory")
	private String empName;
	
	@Column(name = "username")                          //unique
	@NotBlank(message = "Employee username is mandatory")
	private String username;
	
	@Column(name = "password")
	@NotBlank(message = "Enter the password")
	@JsonBackReference
	private String password;
	
	@Column(name = "email_id")                          //unique
	@NotBlank(message = "Enter the email-ID")
	@Email(message = "Email ID is not in format. Please check")
	private String emailId;
	
	@Column(name = "phone_no")                          //unique
	@Pattern(regexp = "^$|[0-9]{10}", message = "Enter a valid Phone No")
	private String phoneNo;

	@NotBlank(message = "Enter the location")
	@Column(name = "location")
	private String location;
	
	@Column(name = "addresses")
	@OneToMany(targetEntity = Address.class, mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference                               //included during serialization
	private Set<Address> addresses = new HashSet<>();

	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String userame) {
		this.username = userame;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Set<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Employee(int empId, String empName, String username, String password, String emailId, String phoneNo,
			String location, Set<Address> addresses) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.username = username;
		this.password = password;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.location = location;
		this.addresses = addresses;
	}
	
	public Employee() {
		super();
	}
	
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", username=" + username + ", password=" + password
				+ ", emailId=" + emailId + ", phoneNo=" + phoneNo + ", location=" + location + ", addresses="
				+ addresses + "]";
	}

}
