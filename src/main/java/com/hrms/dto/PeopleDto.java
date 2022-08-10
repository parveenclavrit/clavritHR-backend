package com.hrms.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

public class PeopleDto {

    private String name;
    private String surname;
    private String emp_status;
    private String email;
    private String job_title;
    private String department;
    private String joining_date;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmp_status() {
        return emp_status;
    }

    public void setEmp_status(String emp_status) {
        this.emp_status = emp_status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(String joining_date) {
        this.joining_date = joining_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PeopleDto(String name, String surname, String emp_status, String email, String job_title, String department, String joining_date, String address) {
        this.name = name;
        this.surname = surname;
        this.emp_status = emp_status;
        this.email = email;
        this.job_title = job_title;
        this.department = department;
        this.joining_date = joining_date;
        this.address = address;
    }
    public PeopleDto(){

    }

}
