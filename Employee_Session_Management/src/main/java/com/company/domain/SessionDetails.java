package com.company.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="session_details")
public class SessionDetails {
	
	@Id
	@Column(name="session_id")
	String sessionId;
	
	@Column(name="username")
	String username;
	
	@Column(name="login_time")
	Timestamp loginTime;
	
	@Column(name="logout_time")
	Timestamp logoutTime;
	
	@Column(name="logged_in_duration")
	String loggedInDuration;
	
	@Column(name="mode_of_logout")
	String modeOfLogout;
	
	public SessionDetails(String sessionId, String username, Timestamp loginTime, Timestamp logoutTime,
			String loggedInDuration, String modeOfLogout) {
		super();
		this.sessionId = sessionId;
		this.username = username;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.loggedInDuration = loggedInDuration;
		this.modeOfLogout = modeOfLogout;
	}
	
	public SessionDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Timestamp getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}
	public Timestamp getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getLoggedInDuration() {
		return loggedInDuration;
	}

	public void setLoggedInDuration(String loggedInDuration) {
		this.loggedInDuration = loggedInDuration;
	}

	public String getModeOfLogout() {
		return modeOfLogout;
	}
	public void setModeOfLogout(String modeOfLogout) {
		this.modeOfLogout = modeOfLogout;
	}
	
	@Override
	public String toString() {
		return "SessionDetails [sessionId=" + sessionId + ", username=" + username + ", loginTime=" + loginTime
				+ ", logoutTime=" + logoutTime + ", loggedInDuration=" + loggedInDuration + ", modeOfLogout="
				+ modeOfLogout + "]";
	}
	
}
