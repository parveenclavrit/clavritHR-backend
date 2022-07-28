package com.hrms.service;

import java.util.Date;

import com.hrms.dto.EmployeeHrmsDetailDto;
import com.hrms.entity.EmployeeHrmsDetail;

public interface EmployeeHrmsDetailService {

	public EmployeeHrmsDetail getEmployee(int id);

	public EmployeeHrmsDetail saveEmployeeHrmsDetails(Date currentDate, EmployeeHrmsDetailDto empHrmsDetails);

}
