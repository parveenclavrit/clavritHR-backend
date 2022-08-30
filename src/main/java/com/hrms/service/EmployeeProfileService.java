package com.hrms.service;

import java.util.List;

import com.hrms.dto.EProfileDataDto;
import com.hrms.entity.EmployeeHrmsDetail;
import com.hrms.entity.EmployeeMaster;
import com.hrms.entity.EmployeePersonalDetail;
import com.hrms.response.EmployeeProfileResDto;

public interface EmployeeProfileService {

	EmployeeProfileResDto createEmployeeProfile(final EProfileDataDto req);

	EProfileDataDto getEmployeeProfile(Integer emp_id) throws Exception;

	List<EProfileDataDto> getAllEmployeeProfile() throws Exception;

	public EProfileDataDto deleteEmployee(Integer emp_id);

      public EProfileDataDto updateEmployeeDetail(EmployeeMaster employeeMaster, EmployeePersonalDetail employeePersonalDetail, EmployeeHrmsDetail employeeHrmsDetail);
	 // public EProfileDataDto updateEmployeeDetail();

}
