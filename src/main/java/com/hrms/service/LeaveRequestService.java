package com.hrms.service;

import java.util.Date;

import com.hrms.dto.LeaveReqDto;
import com.hrms.entity.EmployeeLeaveRequest;

public interface LeaveRequestService {
	
	public EmployeeLeaveRequest getEmployeeLeaveRequestById(Integer id);

	public EmployeeLeaveRequest saveEmployeeLeaveRequest(Date currentDate, LeaveReqDto leaveDto);

	public long getEmployeeLeavesByEmpId(Integer empId);

}
