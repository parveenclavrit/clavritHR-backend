package com.hrms.dto;

import java.util.Date;

public class PeopleDto {
    private String name;
    private String address;
    private String emp_mail;
    private String phone_num;
    private String dob;
    private String joining_date;

    public PeopleDto(){

    }
    public PeopleDto(String name, String address, String emp_mail, String phone_num, String dob,String joining_date){
       super();
        this.name=name;
        this.address=address;
        this.emp_mail=emp_mail;
        this.phone_num=phone_num;
        this.dob=dob;
        this.joining_date=joining_date;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmp_mail() {
        return emp_mail;
    }

    public void setEmp_mail(String emp_mail) {
        this.emp_mail = emp_mail;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(String  joining_date) {
        this.joining_date = joining_date;
    }


}
