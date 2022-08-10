package com.hrms.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class EmployeeLeaveRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int no_of_leave;
	private String from_date;
	private String to_date;
	private String leave_type;
	private String message;
	private Integer emp_id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_on;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_on;

	public EmployeeLeaveRequest() {
		super();
	}

	public EmployeeLeaveRequest(Integer id, int no_of_leave, String from_date, String to_date, String leave_type,
			String message, Integer emp_id, Date created_on, Date updated_on) {
		super();
		this.id = id;
		this.no_of_leave = no_of_leave;
		this.from_date = from_date;
		this.to_date = to_date;
		this.leave_type = leave_type;
		this.message = message;
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

	public int getNo_of_leave() {
		return no_of_leave;
	}

	public void setNo_of_leave(int no_of_leave) {
		this.no_of_leave = no_of_leave;
	}

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}

	public String getLeave_type() {
		return leave_type;
	}

	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
}
