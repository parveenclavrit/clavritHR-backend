package com.hrms.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class EmployeeDocuments {
	private int id ;
	private int emp_id;
	private String pan_card_code;
	private String aadhar_card_code;
	private String offer_letter_code;
	private String salary_slip_code;
	private String signed_document_code;
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
	public String getPan_card_code() {
		return pan_card_code;
	}
	public void setPan_card_code(String pan_card_code) {
		this.pan_card_code = pan_card_code;
	}
	public String getAadhar_card_code() {
		return aadhar_card_code;
	}
	public void setAadhar_card_code(String aadhar_card_code) {
		this.aadhar_card_code = aadhar_card_code;
	}
	public String getOffer_letter_code() {
		return offer_letter_code;
	}
	public void setOffer_letter_code(String offer_letter_code) {
		this.offer_letter_code = offer_letter_code;
	}
	public String getSalary_slip_code() {
		return salary_slip_code;
	}
	public void setSalary_slip_code(String salary_slip_code) {
		this.salary_slip_code = salary_slip_code;
	}
	public String getSigned_document_code() {
		return signed_document_code;
	}
	public void setSigned_document_code(String signed_document_code) {
		this.signed_document_code = signed_document_code;
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
