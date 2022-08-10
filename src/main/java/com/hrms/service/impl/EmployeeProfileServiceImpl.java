package com.hrms.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.dto.EProfileDataDto;
import com.hrms.dto.EmployeeHrmsDetailDto;
import com.hrms.dto.EmployeeMasterDto;
import com.hrms.dto.EmployeePersonalDetailsDto;
import com.hrms.entity.EmployeeHrmsDetail;
import com.hrms.entity.EmployeeMaster;
import com.hrms.entity.EmployeePersonalDetail;
import com.hrms.response.EmployeeProfileResDto;
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
	@Transactional(rollbackFor = Exception.class)
	public EmployeeProfileResDto createEmployeeProfile(EProfileDataDto req) {
		
		final Date currentDate = new Date();
		EmployeeHrmsDetailDto hrmsDto = req.getEmpHrmsDetails();
		EmployeePersonalDetailsDto personalDto = req.getEmpPersonalDetails();
		EmployeeMaster empMasterEntity = eMasterService.saveEmployeeMaster(currentDate, req.getEmpMasterDetails());
		hrmsDto.setEmp_id(empMasterEntity.getId());
		personalDto.setEmp_id(empMasterEntity.getId());
		employeeHrmsService.saveEmployeeHrmsDetails(currentDate, hrmsDto);
		empPeronalDetailsService.saveEmployeePersonalDetails(currentDate, personalDto);
		EmployeeProfileResDto response = new EmployeeProfileResDto();
		response.setCode(200);
		response.setMessage("Employee profile created successfully");
		return response;
	}

	@Override
	public EProfileDataDto getEmployeeProfile(Integer emp_id) throws Exception {
		EmployeeMaster empEntity = eMasterService.getEmployee(emp_id);
		EmployeeHrmsDetail hrmsEntity = employeeHrmsService.getEmployeeByEmployeeId(emp_id);
		EmployeePersonalDetail personalDetails = empPeronalDetailsService.getEmployeePersonalDetailsByEmployeeId(emp_id);
		ObjectMapper mapper = new ObjectMapper();
		EmployeeMasterDto empMasterDto =  mapper.readValue(mapper.writeValueAsString(empEntity), EmployeeMasterDto.class);
		EmployeeHrmsDetailDto hrmsDto =  mapper.readValue(mapper.writeValueAsString(hrmsEntity), EmployeeHrmsDetailDto.class);
		EmployeePersonalDetailsDto personalDto  =  mapper.readValue(mapper.writeValueAsString(personalDetails), EmployeePersonalDetailsDto.class);
		return new EProfileDataDto(personalDto, hrmsDto, empMasterDto);
	}

}
