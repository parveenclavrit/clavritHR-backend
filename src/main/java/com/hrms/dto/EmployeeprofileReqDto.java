package com.hrms.dto;

import java.util.Date;

public class EmployeeprofileReqDto {
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public int getCasualLeaves() {
		return casualLeaves;
	}

	public void setCasualLeaves(int casualLeaves) {
		this.casualLeaves = casualLeaves;
	}

	public int getSickLeaves() {
		return sickLeaves;
	}

	public void setSickLeaves(int sickLeaves) {
		this.sickLeaves = sickLeaves;
	}

	public int getEarnLeaves() {
		return earnLeaves;
	}

	public void setEarnLeaves(int earnLeaves) {
		this.earnLeaves = earnLeaves;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public char getActive() {
		return active;
	}

	public void setActive(char active) {
		this.active = active;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOther_field_1() {
		return other_field_1;
	}

	public void setOther_field_1(String other_field_1) {
		this.other_field_1 = other_field_1;
	}

	public String getOther_field_2() {
		return other_field_2;
	}

	public void setOther_field_2(String other_field_2) {
		this.other_field_2 = other_field_2;
	}

	private String email;
	private String department;
	private Date doj;
	private int casualLeaves;
	private int sickLeaves;
	private int earnLeaves;
	private String role;
	private char active;
	private String type;
	private String password;
	private String name;
	private String gender;
	private int age;
	private String address;
	private String other_field_1;
	private String other_field_2;

public EmployeeprofileReqDto(String email, String department, Date doj, int casualLeaves, int sickLeaves, int earnLeaves,
		String role, char active, String type, String password, String name, String gender, int age, String address,
		String other_field_1, String other_field_2) {
	super();
	this.email = email;
	this.department = department;
	this.doj = doj;
	this.casualLeaves = casualLeaves;
	this.sickLeaves = sickLeaves;
	this.earnLeaves = earnLeaves;
	this.role = role;
	this.active = active;
	this.type = type;
	this.password = password;
	this.name = name;
	this.gender = gender;
	this.age = age;
	this.address = address;
	this.other_field_1 = other_field_1;
	this.other_field_2 = other_field_2;
}
}


