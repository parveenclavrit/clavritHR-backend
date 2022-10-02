package com.hrms.service;

import java.util.Date;
import java.util.List;

import com.hrms.dto.EProfileDataDto;
import com.hrms.dto.EmployeeMasterDto;
import com.hrms.entity.EmployeeMaster;


public interface EmployeeMasterService {

	public EmployeeMaster getEmployee(int id);

	public List<EmployeeMaster> getEmployeeByList(String idListSepByComma);

	public EmployeeMaster saveEmployeeMaster(Date currentDate, EmployeeMasterDto employeeMasterDto);

	public List<EmployeeMaster> getAllEmployee();

	public void EmployeedeleteById(Integer id);

	public EmployeeMaster updateEmp(Date currentDate,Integer id,EmployeeMasterDto employeeMaster);
}
