package com.hrms.dto;

import java.util.List;

public class EmployeeProfileResDto {

	String test;
	List<String> errors;
	
	public EmployeeProfileResDto() {}
	
	public void setTest(String test) {
		this.test = test;
	}
	public String getTest() {
		return this.test;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
}
