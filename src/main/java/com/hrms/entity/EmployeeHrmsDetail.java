package com.hrms.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class EmployeeHrmsDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String department;
	private Date doj;
	private int casual_leaves;
	private int sick_leaves;
	private int earn_leaves;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id")
	private EmployeeMaster employeeMaster;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_on;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_on;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getCasual_leaves() {
		return casual_leaves;
	}

	public void setCasual_leaves(int casual_leaves) {
		this.casual_leaves = casual_leaves;
	}

	public int getSick_leaves() {
		return sick_leaves;
	}

	public void setSick_leaves(int sick_leaves) {
		this.sick_leaves = sick_leaves;
	}

	public int getEarn_leaves() {
		return earn_leaves;
	}

	public void setEarn_leaves(int earn_leaves) {
		this.earn_leaves = earn_leaves;
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
	public EmployeeMaster getEmployeeMaster() {
		return employeeMaster;
	}

	public void setEmployeeMaster(EmployeeMaster employeeMaster) {
		this.employeeMaster = employeeMaster;
	}
}
