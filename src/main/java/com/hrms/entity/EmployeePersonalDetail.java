package com.hrms.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class EmployeePersonalDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer emp_id;
	private String name;
	private String gender;
	private int age;
	private String address;
	private String other_field_1;
	private String other_field_2;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id", referencedColumnName = "id",  insertable = false, updatable = false)

	private EmployeeMaster employeeMaster;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_on;

	@Temporal(TemporalType.TIMESTAMP)
	private Date utilDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date udated_on;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOther_field_1() {
		return other_field_1;
	}

	public void setOther_field_1(String other_field_1) {
		this.other_field_1 = other_field_1;
	}

	public String getOther_field_2() {
		return other_field_2;
	}

	public void setOther_field_2(String other_field_2) {
		this.other_field_2 = other_field_2;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public Date getUtilDate() {
		return utilDate;
	}

	public void setUtilDate(Date utilDate) {
		this.utilDate = utilDate;
	}

	public Date getUdated_on() {
		return udated_on;
	}

	public void setUdated_on(Date udated_on) {
		this.udated_on = udated_on;
	}
	public EmployeeMaster getEmployeeMaster() {
		return employeeMaster;
	}

	public void setEmployeeMaster(EmployeeMaster employeeMaster) {
		this.employeeMaster = employeeMaster;
	}
}
