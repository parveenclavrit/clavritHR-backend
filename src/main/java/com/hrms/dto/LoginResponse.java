package com.hrms.dto;

public class LoginResponse {

	private String userRole;
	
	private String jwtToken;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public LoginResponse(String userRole, String jwtToken) {
		super();
		this.userRole = userRole;
		this.jwtToken = jwtToken;
	}
	
	
	
	

}
