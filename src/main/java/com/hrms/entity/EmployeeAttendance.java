package com.hrms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class EmployeeAttendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="emp_id")
	private int empId;
	@Column(name="punch_in")
	private Date punchIn;
	@Column(name="punch_out")
	private Date punchOut;
	@Column(name="created_on")
	private Date createdOn;
	@Column(name="udated_on")
	private Date udatedOn;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public Date getPunchIn() {
		return punchIn;
	}

	public void setPunchIn(Date punchIn) {
		this.punchIn = punchIn;
	}

	public Date getPunchOut() {
		return punchOut;
	}

	public void setPunchOut(Date punchOut) {
		this.punchOut = punchOut;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUdatedOn() {
		return udatedOn;
	}

	public void setUdatedOn(Date udatedOn) {
		this.udatedOn = udatedOn;
	}

}
