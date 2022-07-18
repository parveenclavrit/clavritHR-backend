package com.hrms.dto;

public class LeaveReqDto {
	
	private String days;
	private String leaveType;
	private String message;
	
	public LeaveReqDto() {
		super();
	}

	public LeaveReqDto(String days, String leaveType, String message) {
		super();
		this.days = days;
		this.leaveType = leaveType;
		this.message = message;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "LeaveReqDto [days=" + days + ", leaveType=" + leaveType + ", message=" + message + "]";
	}
	
}
