package com.hrms.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hrms.dto.EmployeePersonalDetailsDto;
import com.hrms.entity.EmployeeMaster;
import com.hrms.entity.EmployeePersonalDetail;


public interface EmployeePersonalDetailService {

	public EmployeePersonalDetail getEmpPersonalDetailsById(Integer id);

	public EmployeePersonalDetail saveEmployeePersonalDetails(Date currentDate, EmployeePersonalDetailsDto personalDto);

	public EmployeePersonalDetail getEmployeePersonalDetailsByEmployeeId(Integer emp_id);

	public Map<Integer, String> getAllEmployeeNamesByIds(List<Integer> empIds);
	public EmployeePersonalDetail updateEmp2(EmployeePersonalDetail employeePersonalDetail);

}
