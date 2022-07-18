package com.hrms.service;

import com.hrms.entity.EmployeeHrmsDetail;


public interface EmployeeHrmsDetailService {

	public EmployeeHrmsDetail getEmployee(int id);
	public EmployeeHrmsDetail saveEmployeeMaster(EmployeeHrmsDetail employeeHrmsDetail);


}
