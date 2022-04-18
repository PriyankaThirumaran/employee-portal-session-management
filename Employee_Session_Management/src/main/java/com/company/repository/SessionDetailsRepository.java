package com.company.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.company.domain.SessionDetails;

public interface SessionDetailsRepository extends JpaRepository<SessionDetails, Integer>{
	
	//insert session detail
	//saveAndFlush(S Entity)
	
	//Fetching the session details
	@Query(value = "SELECT * FROM Session_Details WHERE username = :username", nativeQuery = true)
	public List<SessionDetails> fetchSession(String username);
}
