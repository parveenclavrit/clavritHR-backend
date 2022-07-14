package com.clavrit.dto;

public class LeaveReqDto {
	
	private String message;
	private String hour;
	
	public LeaveReqDto() {
		super();
	}
	
	public LeaveReqDto(String message, String hour) {
		super();
		this.message = message;
		this.hour = hour;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	@Override
	public String toString() {
		return "LeaveReqDto [message=" + message + ", hour=" + hour + "]";
	}
	
}
