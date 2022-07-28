package com.hrms.service;

import com.hrms.dto.EProfileDataDto;
import com.hrms.dto.EmployeeProfileResDto;

public interface EmployeeProfileService {

	EmployeeProfileResDto createEmployeeProfile(final EProfileDataDto req);

}
