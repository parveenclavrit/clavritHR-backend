package com.hrms.entity;

import javax.persistence.*;

@Entity
@Table (name="file_upload")
public class FileUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="emp_id")
    private int empId;
    @Column(name="file_name")
    private String fileName;
	public FileUpload(int id, int empId, String fileName) {
		super();
		this.id = id;
		this.empId = empId;
		this.fileName = fileName;
	}
	public FileUpload() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
   
    
}
