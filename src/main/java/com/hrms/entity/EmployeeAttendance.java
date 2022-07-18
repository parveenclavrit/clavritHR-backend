package com.hrms.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class EmployeeAttendance {

	private int id;
	private int emp_id;
	private Date punch_in;
	private Date punch_out;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_on;
	@Temporal(TemporalType.TIMESTAMP)
	private Date udated_on;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public Date getPunch_in() {
		return punch_in;
	}

	public void setPunch_in(Date punch_in) {
		this.punch_in = punch_in;
	}

	public Date getPunch_out() {
		return punch_out;
	}

	public void setPunch_out(Date punch_out) {
		this.punch_out = punch_out;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public Date getUdated_on() {
		return udated_on;
	}

	public void setUdated_on(Date udated_on) {
		this.udated_on = udated_on;
	}

}
