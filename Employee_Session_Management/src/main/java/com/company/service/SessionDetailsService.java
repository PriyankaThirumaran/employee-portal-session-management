package com.company.service;

import java.util.List;

import com.company.domain.SessionDetails;

public interface SessionDetailsService {
	
	//Insert a session
	public void insertSession(SessionDetails sessionDetails);
	
	//Fetching the session details
	public List<SessionDetails> fetchSession(String username);

}
