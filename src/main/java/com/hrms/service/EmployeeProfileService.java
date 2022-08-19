package com.hrms.service;

import java.util.List;

import com.hrms.dto.EProfileDataDto;
import com.hrms.response.EmployeeProfileResDto;

public interface EmployeeProfileService {

	EmployeeProfileResDto createEmployeeProfile(final EProfileDataDto req);

	EProfileDataDto getEmployeeProfile(Integer emp_id) throws Exception;

	List<EProfileDataDto> getAllEmployeeProfile() throws Exception;

}
