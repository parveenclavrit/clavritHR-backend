package com.hrms.dto;

import java.util.Date;

public class EmployeeHrmsDetailDto {

	private Integer id;
	private String email;
	private String department;
	private Date doj;
	private int sick_leaves;
	private Integer emp_id;
	private Date created_on;
	private Date updated_on;

	public EmployeeHrmsDetailDto() {
		super();
	}

	public EmployeeHrmsDetailDto(Integer id, String email, String department, Date doj,int sick_leaves, Integer emp_id, Date created_on, Date updated_on) {
		super();
		this.id = id;
		this.email = email;
		this.department = department;
		this.doj = doj;
		this.sick_leaves = sick_leaves;
		this.emp_id = emp_id;
		this.created_on = created_on;
		this.updated_on = updated_on;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public int getSick_leaves() {
		return sick_leaves;
	}

	public void setSick_leaves(int sick_leaves) {
		this.sick_leaves = sick_leaves;
	}
	
	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public Date getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}

	@Override
	public String toString() {
		return "EmployeeHrmsDetailDto [id=" + id + ", email=" + email + ", department=" + department + ", doj=" + doj
				+ ", sick_leaves=" + sick_leaves + ",emp_id = " + emp_id + ", created_on=" + created_on + ", updated_on=" + updated_on + "]";
	}

}
