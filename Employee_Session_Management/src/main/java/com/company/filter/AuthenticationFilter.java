package com.company.filter;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.company.controller.AuthenticationController;
import com.company.exceptions.PleaseLoginException;
import com.company.exceptions.SessionTimedOutException;



@Component("authenticationFilter")
public class AuthenticationFilter extends OncePerRequestFilter {
	
//	@Autowired
//	private SessionDetailsServiceImpl sessionDetailsService;
	
	@Autowired
	private AuthenticationController authenticationController;
	
	static int sessionTimedOut = 5;                                            //session time out happens when screen is inactive for 5 mins
	
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException, PleaseLoginException,SessionTimedOutException {
		//incoming request has come
		
		System.out.println("Authentication filter entered");
		
		HttpServletRequest httpRequest = request;
		String endpoint = httpRequest.getRequestURI();
		
		System.out.println("Incoming API call is " + endpoint);
		
		HttpSession session = httpRequest.getSession(false);
		if (session == null) {
			throw new PleaseLoginException("Please login to access");
		}
		
		boolean isSessionTimedOut = Instant.now().isAfter(Instant.ofEpochMilli(session.getLastAccessedTime()).
				plus(sessionTimedOut, ChronoUnit.MINUTES));
		
		if (isSessionTimedOut) {
			
			System.out.println("time out");
			
			session.setAttribute("logout_method", "Timed out");
			authenticationController.logoutEmployee(httpRequest);
		    
//		    //delete the session
//			session.invalidate();
//			throw new SessionTimedOutException("Session timed out. Please login again");
		}
		
		chain.doFilter(httpRequest, response);
	}

}
