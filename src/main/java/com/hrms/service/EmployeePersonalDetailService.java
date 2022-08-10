package com.hrms.service;

import java.util.Date;

import com.hrms.dto.EmployeePersonalDetailsDto;
import com.hrms.entity.EmployeePersonalDetail;


public interface EmployeePersonalDetailService {

	public EmployeePersonalDetail getEmpPersonalDetailsById(Integer id);

	public EmployeePersonalDetail saveEmployeePersonalDetails(Date currentDate, EmployeePersonalDetailsDto personalDto);

	public EmployeePersonalDetail getEmployeePersonalDetailsByEmployeeId(Integer emp_id);


}
