package com.hrms.service;

import java.util.List;

import com.hrms.entity.EmployeeHiringDetail;


public interface EmployeeHiringSevice {
	
 EmployeeHiringDetail getHiringDetails(int id);


	List<EmployeeHiringDetail> getALLEmployeeHiringDetails();
	void deleteEmployeeHiringDetail(int id);
	EmployeeHiringDetail saveBook(EmployeeHiringDetail hiringdetail);


}
