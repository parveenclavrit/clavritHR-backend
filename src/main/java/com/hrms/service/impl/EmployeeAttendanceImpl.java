package com.hrms.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.dto.EmployeeAttendanceDto;
import com.hrms.entity.EmployeeAttendance;
import com.hrms.repository.EmployeeAttendanceRepository;
import com.hrms.service.EmployeeAttendanceService;

@Service
@Transactional
public class EmployeeAttendanceImpl implements EmployeeAttendanceService {
	
	@Autowired
	EmployeeAttendanceRepository eAttendanceRepo;

	@Override
	public EmployeeAttendance getEmployeeAttendance(int id) {
		Optional<EmployeeAttendance> AttendanceList = this.eAttendanceRepo.findById(id);
		return ! AttendanceList.isPresent() ? null : AttendanceList.get();
	}
	
	@Override
	public List<EmployeeAttendance> getEmployeeAttendanceByList(String idListSepByComma) {
		List<Integer> listInt = Arrays.asList(idListSepByComma.split(",")).stream().map(e -> Integer.parseInt(e))
				.collect(Collectors.toList());
		return eAttendanceRepo.findAllById(listInt);
	}

	@Override
	public EmployeeAttendance saveEmployeeAttendance(EmployeeAttendance employeeAttendance) {
		return this.eAttendanceRepo.save(employeeAttendance);
	}

	@Override
	public EmployeeAttendance updateEmployeeAttendance(EmployeeAttendance employeeAttendance) {
		return this.eAttendanceRepo.save(employeeAttendance);
	}

	@Override
	public List<EmployeeAttendance> getEmAttendanceBetweenDate(Date startDate, Date endDate, int empId) {
		return this.eAttendanceRepo.findAllWithEmpId(startDate, endDate, empId);
	}

	@Override
	public EmployeeAttendance findTodayAttendenceByEmpId(Integer empId) {
		return this.eAttendanceRepo.findTodayAttendenceByEmpId(empId);
	}

	@Override
	public List<EmployeeAttendance> getEmAttendanceBetweenDateAndTime(String startDate, String endDate, Integer empId) {
		if(null != empId) {
			return this.eAttendanceRepo.findAllWithEmpIdBetweenStartDateAndEndDate(startDate, endDate, empId);
		}
		return this.eAttendanceRepo.findAllWithCreationDateBetweenCustom(startDate, endDate);
	}
	
	
	@Transactional
	public List<EmployeeAttendance> saveAllEmployeeAttendance(List<EmployeeAttendanceDto> attendanceDtoList) {
	    Date now = new Date();

	    // Load existing attendance
	    List<EmployeeAttendance> existingList = eAttendanceRepo.findAll();
	    Map<String, EmployeeAttendance> existingMap = new HashMap<>();
	    for (EmployeeAttendance e : existingList) {
	        String key = e.getEmpId() + "|" + (e.getPunchIn() != null ? e.getPunchIn().getTime() : "");
	        if (!existingMap.containsKey(key)) {
	            existingMap.put(key, e);
	        }
	    }

	    List<EmployeeAttendance> toSave = new ArrayList<>();
	    Set<String> seenKeys = new HashSet<>();

	    for (EmployeeAttendanceDto dto : attendanceDtoList) {
	        String key = dto.getEmpId() + "|" + (dto.getPunchIn() != null ? dto.getPunchIn().getTime() : "");

	        // Skip duplicates within Excel
	        if (seenKeys.contains(key)) continue;
	        seenKeys.add(key);

	        if (existingMap.containsKey(key)) {
	            // Update existing record
	            EmployeeAttendance existing = existingMap.get(key);
	            existing.setPunchOut(dto.getPunchOut());
	            existing.setUdatedOn(now);
	            toSave.add(existing);
	        } else {
	            // Create new entity
	            EmployeeAttendance newEntity = new EmployeeAttendance();
	            newEntity.setEmpId(dto.getEmpId());
	            newEntity.setPunchIn(dto.getPunchIn());
	            newEntity.setPunchOut(dto.getPunchOut());
	            newEntity.setCreatedOn(dto.getCreatedOn() != null ? dto.getCreatedOn() : now);
	            newEntity.setUdatedOn(dto.getUdatedOn() != null ? dto.getUdatedOn() : now);
	            toSave.add(newEntity);
	        }
	    }

	    return eAttendanceRepo.saveAll(toSave);
	}

	
}
