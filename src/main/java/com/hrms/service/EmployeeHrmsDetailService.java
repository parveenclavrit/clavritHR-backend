package com.hrms.service;

import java.util.Date;

import com.hrms.dto.EmployeeHrmsDetailDto;
import com.hrms.entity.EmployeeHrmsDetail;

public interface EmployeeHrmsDetailService {

	public EmployeeHrmsDetail getEmployee(Integer id);

	EmployeeHrmsDetail saveEmployeeHrmsDetails(Date currentDate, EmployeeHrmsDetailDto hrmsDto);

	public EmployeeHrmsDetail getEmployeeByEmployeeId(Integer emp_id);

public EmployeeHrmsDetail updateEmp3(EmployeeHrmsDetail employeeHrmsDetail);
}
