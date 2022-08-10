package com.hrms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table (name="hiring")
public class EmployeeHiringDetail {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private String candidate_info;
	private String jobOpenings;
	private String Status;
	private String lastEmail;
	
	public String getCandidate_info() {
		return candidate_info;
	}
	public void setCandidate_info(String candidate_info) {
		this.candidate_info = candidate_info;
	}
	public String getJobOpenings() {
		return jobOpenings;
	}
	public void setJobOpenings(String jobOpenings) {
		this.jobOpenings = jobOpenings;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getLastEmail() {
		return lastEmail;
	}
	public void setLastEmail(String lastEmail) {
		this.lastEmail = lastEmail;
	}
	
}
