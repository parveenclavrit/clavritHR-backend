package com.hrms.entity;

import java.util.Date;

public class EmployeeLeaveRequest {

private int id;
private int emp_id;
private String leave_type;
private Date from_date;
private Date to_date;
private int total_days;
private int approving_emp_id;
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
public String getLeave_type() {
	return leave_type;
}
public void setLeave_type(String leave_type) {
	this.leave_type = leave_type;
}
public Date getFrom_date() {
	return from_date;
}
public void setFrom_date(Date from_date) {
	this.from_date = from_date;
}
public Date getTo_date() {
	return to_date;
}
public void setTo_date(Date to_date) {
	this.to_date = to_date;
}
public int getTotal_days() {
	return total_days;
}
public void setTotal_days(int total_days) {
	this.total_days = total_days;
}
public int getApproving_emp_id() {
	return approving_emp_id;
}
public void setApproving_emp_id(int approving_emp_id) {
	this.approving_emp_id = approving_emp_id;
}

}
