package com.hrms.service;

import java.util.List;

import com.hrms.entity.EmployeeMaster;


public interface EmployeeMasterService {

	public EmployeeMaster getEmployee(int id);

	public List<EmployeeMaster> getEmployeeByList(String idListSepByComma);
	public EmployeeMaster saveEmployeeMaster(EmployeeMaster employeeMaster);


}
