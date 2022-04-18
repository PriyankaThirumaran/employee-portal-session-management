package com.company.controller;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.SessionDetails;
import com.company.exceptions.SessionTimedOutException;
import com.company.service.AuthenticationServiceImpl;
import com.company.service.EmployeeServiceImpl;
import com.company.service.SessionDetailsServiceImpl;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationServiceImpl authenticationService;
	
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@Autowired
	private SessionDetailsServiceImpl sessionDetailsService;
	
	//Login functionality
	@GetMapping("/login/{username}/{password}")
	@ResponseBody
	public String loginEmployee(HttpServletRequest request, @PathVariable("username") String username, @PathVariable("password") String password) {
		
		if (authenticationService.validateCredentials(username, password)) {
			if (request.getSession(false) == null) {                         //checking if session is already present (not present)
				HttpSession session = request.getSession();                  //creating new session
				
				//to use this username to store session details
				session.setAttribute("username", username);
				
				//to restrict the access of other employee details
				int empId = employeeService.findEmpId(username);
				session.setAttribute("empId", empId);
				
				return "Login Successful";
			}
			else {
				return "You are already logged in";
			}
		}
		else
			return "Invalid credentials";
	}
	
	
	//Logout functionality
	@GetMapping("/logout")
	@ResponseBody
	public String logoutEmployee(HttpServletRequest request) throws SessionTimedOutException{
		if (request.getSession(false) == null)                        //checking if session already present (not present)
			return "Please Login";
		else {
			HttpSession session = request.getSession();                  //getting the existing session
			
			//save session in some database
			String sessionId = session.getId();
			String username = (String) session.getAttribute("username");
			long loginT = session.getCreationTime();
			Timestamp loginTime = new Timestamp(loginT);
			
			//This method returns the time in millis
			Calendar calendar = Calendar.getInstance();
		    long logoutT = calendar.getTimeInMillis();
		    Timestamp logoutTime = new Timestamp(logoutT);
		    
		    String modeOfLogout;
		    if (session.getAttribute("logout_method")  == null)
		    	modeOfLogout = "logout";
		    else
		    	modeOfLogout = "Timed out";
		    
		    String loggedInDuration;
//		    Timestamp duration = logoutTime - loginTime;
		    Long difference = (logoutTime.getTime() - loginTime.getTime()) - 19800000;     // minus 5:30 hrs in millis
		    System.out.println(difference);
		    Timestamp duration = new Timestamp(difference);
		    System.out.println(duration);
		    String time = duration.toString();
		    String arr[]=time.split(" ");
		    loggedInDuration = arr[1];
		    
		    //insert session detail to db
		    SessionDetails sessionDetails = new SessionDetails(sessionId, username, loginTime, logoutTime, loggedInDuration, modeOfLogout);
		    sessionDetailsService.insertSession(sessionDetails);
		    
		    //delete the session
			session.invalidate();
			
			if (modeOfLogout == "logout")
				return "Logout Successful";
			else
				throw new SessionTimedOutException("Session timed out. Please login again");
		}	
	}
}
