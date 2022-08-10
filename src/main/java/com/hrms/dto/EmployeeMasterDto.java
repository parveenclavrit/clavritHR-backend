package com.hrms.dto;


import java.util.Date;

public class EmployeeMasterDto {
	
	private Integer id;
	private String role;
	private Character active;
	private String type;
	private String password;
	private Date created_on;
	private Date updated_on;
	
	public EmployeeMasterDto() {}
	
	public EmployeeMasterDto(Integer id, String role, Character active, String type, String password, Date created_on,
			Date updated_on) {
		super();
		this.id = id;
		this.role = role;
		this.active = active;
		this.type = type;
		this.password = password;
		this.created_on = created_on;
		this.updated_on = updated_on;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Character getActive() {
		return active;
	}
	public void setActive(Character active) {
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
		return "EmployeeMasterDto [id=" + id + ", role=" + role + ", active=" + active + ", type=" + type
				+ ", password=" + password + ", created_on=" + created_on + ", updated_on=" + updated_on + "]";
	}


	
}
