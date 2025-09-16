package com.hrms.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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
	
	
	
	public List<EmployeeLeaveRequest> saveAllLeaveRequests(List<LeaveReqDto> leaveDtoList) {
        Date now = new Date();

        // Load existing leave requests
        List<EmployeeLeaveRequest> existingList = eLeaveRepo.findAll();
        Map<String, EmployeeLeaveRequest> existingMap = new HashMap<>();
        for (EmployeeLeaveRequest e : existingList) {
            String key = e.getEmp_id() + "|" + e.getFrom_date() + "|" + e.getTo_date();
            existingMap.put(key, e);
        }

        List<EmployeeLeaveRequest> toSave = new ArrayList<>();
        Set<String> seenKeys = new HashSet<>();

        for (LeaveReqDto dto : leaveDtoList) {
            String key = dto.getEmp_id() + "|" + dto.getFrom_date() + "|" + dto.getTo_date();

            // Skip duplicates in Excel sheet
            if (seenKeys.contains(key)) continue;
            seenKeys.add(key);

            if (existingMap.containsKey(key)) {
                // Update existing record
                EmployeeLeaveRequest existing = existingMap.get(key);
                existing.setNo_of_leave(dto.getNo_of_leave());
                existing.setLeave_type(dto.getLeave_type());
                existing.setMessage(dto.getMessage());
                existing.setUpdated_on(now);
                toSave.add(existing);
            } else {
                // Create new record
                EmployeeLeaveRequest newLeave = new EmployeeLeaveRequest();
                newLeave.setEmp_id(dto.getEmp_id());
                newLeave.setNo_of_leave(dto.getNo_of_leave());
                newLeave.setFrom_date(dto.getFrom_date());
                newLeave.setTo_date(dto.getTo_date());
                newLeave.setLeave_type(dto.getLeave_type());
                newLeave.setMessage(dto.getMessage());
                newLeave.setCreated_on(now);
                newLeave.setUpdated_on(now);
                toSave.add(newLeave);
            }
        }

        // Save all new/updated records
        return eLeaveRepo.saveAll(toSave);
    }
	
	
}
