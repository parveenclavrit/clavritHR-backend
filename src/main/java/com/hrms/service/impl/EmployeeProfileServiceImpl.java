package com.hrms.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrms.dto.EProfileDataDto;
import com.hrms.dto.EmployeePersonalDetailsDto;
import com.hrms.dto.EmployeeProfileResDto;
import com.hrms.entity.EmployeeMaster;
import com.hrms.service.EmployeeHrmsDetailService;
import com.hrms.service.EmployeeMasterService;
import com.hrms.service.EmployeePersonalDetailService;
import com.hrms.service.EmployeeProfileService;

@Component
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

	@Autowired
	EmployeeMasterService eMasterService;
	
	@Autowired
	EmployeeHrmsDetailService employeeHrmsService;
	
	@Autowired
	EmployeePersonalDetailService empPeronalDetailsService;

	@Override
	public EmployeeProfileResDto createEmployeeProfile(EProfileDataDto req) {
		
		final Date currentDate = new Date();
		EmployeeMaster empMasterEntity = eMasterService.saveEmployeeMaster(currentDate, req.getEmpMasterDetails());
		employeeHrmsService.saveEmployeeHrmsDetails(currentDate, req.getEmpHrmsDetails());
		EmployeePersonalDetailsDto personalDto = req.getEmpPersonalDetails();
		personalDto.setEmp_id(empMasterEntity.getId());
		empPeronalDetailsService.saveEmployeePersonalDetails(currentDate, personalDto);
		EmployeeProfileResDto response = new EmployeeProfileResDto();
		response.setTest("Employee profile created successfully");
		return response;
	}
	
}
