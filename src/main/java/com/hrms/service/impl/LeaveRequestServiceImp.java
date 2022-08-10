package com.hrms.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.dto.LeaveReqDto;
import com.hrms.entity.EmployeeLeaveRequest;
import com.hrms.repository.EmployeeLeaveRequestRepository;
import com.hrms.service.LeaveRequestService;

@Service
public class LeaveRequestServiceImp implements LeaveRequestService {

	@Autowired
	EmployeeLeaveRequestRepository eLeaveRepo;

	@Override
	public EmployeeLeaveRequest getEmployeeLeaveRequestById(Integer id) {
		Optional<EmployeeLeaveRequest> EmployeeLeaveList = this.eLeaveRepo.findById(id);
		return !EmployeeLeaveList.isPresent() ? null : EmployeeLeaveList.get();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EmployeeLeaveRequest saveEmployeeLeaveRequest(Date currentDate, LeaveReqDto leaveDto) {
		EmployeeLeaveRequest eLeave = new EmployeeLeaveRequest();
		eLeave.setFrom_date(leaveDto.getFrom_date());
		eLeave.setLeave_type(leaveDto.getLeave_type());
		eLeave.setNo_of_leave(leaveDto.getNo_of_leave());
		eLeave.setTo_date(leaveDto.getTo_date());
		eLeave.setEmp_id(leaveDto.getEmp_id());
		eLeave.setMessage(leaveDto.getMessage());
		eLeave.setCreated_on(currentDate);
		eLeave.setUpdated_on(currentDate);
		return eLeaveRepo.save(eLeave);
	}
}
