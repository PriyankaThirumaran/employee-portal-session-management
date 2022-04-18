package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.company.domain.SessionDetails;
import com.company.repository.SessionDetailsRepository;

@Service("sessionDetailsService")
@Configurable
public class SessionDetailsServiceImpl implements SessionDetailsService{
	
	@Autowired
	private SessionDetailsRepository sessionDetailsRepository;
	
	//Insert a session
	public void insertSession(SessionDetails sessionDetails) {
		sessionDetailsRepository.saveAndFlush(sessionDetails);
	}
	
	//Fetching the session details
	public List<SessionDetails> fetchSession(String username) {
		return sessionDetailsRepository.fetchSession(username);
	}

}
