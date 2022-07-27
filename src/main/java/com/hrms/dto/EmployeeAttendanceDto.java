package com.hrms.dto;

import java.util.Date;


public class EmployeeAttendanceDto {

	private int id;
	private int empId;
	private Date punchIn;
	private Date punchOut;
	private Date createdOn;
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
