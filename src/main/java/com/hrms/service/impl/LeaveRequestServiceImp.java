package com.hrms.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

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
		if (this.checkIfLeaveAlreadyAppliedForDate(leaveDto, currentDate)) {
			return null;
		}
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

	private boolean checkIfLeaveAlreadyAppliedForDate(LeaveReqDto leaveDto, Date currentDate) {
		List<EmployeeLeaveRequest> list = eLeaveRepo.findAllByEmployeeIdAndDateBetween(leaveDto.getEmp_id(),
				leaveDto.getFrom_date(), leaveDto.getTo_date());
		if (list.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public long getEmployeeLeavesByEmpId(Integer empId) {
		LocalDate currentDate = LocalDate.now();
		LocalDate startOfYear = LocalDate.of(currentDate.getYear(), 01, 01);
		LocalDate endOfYear = LocalDate.of(currentDate.getYear(), 12, 31);
		List<EmployeeLeaveRequest> employeeLeavesList = eLeaveRepo.findAllByEmployeeIdAndDateBetween(empId,
				startOfYear.toString(), endOfYear.toString());
		AtomicLong totalLeaves = new AtomicLong();
		employeeLeavesList.stream().forEach(empLeave-> {
			LocalDate leaveFromDate = LocalDate.parse(empLeave.getFrom_date());
			LocalDate leaveToDate = LocalDate.parse(empLeave.getTo_date());
			long totalDaysOfLeave = 0;
			if(leaveFromDate.getYear()<startOfYear.getYear()) {
				totalDaysOfLeave = startOfYear.until(leaveToDate, ChronoUnit.DAYS) + 1;
			} else if(leaveToDate.getYear() > endOfYear.getYear()) {
				totalDaysOfLeave = leaveFromDate.until(endOfYear, ChronoUnit.DAYS) + 1;
			} else {
				totalDaysOfLeave = leaveFromDate.until(leaveToDate, ChronoUnit.DAYS) + 1;
			}
			totalLeaves.addAndGet(totalDaysOfLeave);
		});
		return totalLeaves.get();
	}
	
	public static void main(String args[]) {
		LocalDate toDate = LocalDate.parse("2022-01-05");
		LocalDate currentDate = LocalDate.now();
		LocalDate startOfYear = LocalDate.of(currentDate.getYear(), 01, 01);
		long totalDaysOfLeave = startOfYear.until(toDate, ChronoUnit.DAYS) + 1;
		System.out.println(totalDaysOfLeave);
	}
	
	
}
