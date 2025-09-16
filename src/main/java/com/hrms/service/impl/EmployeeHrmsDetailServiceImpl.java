package com.hrms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.hrms.entity.EmployeeMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.dto.EmployeeHrmsDetailDto;
import com.hrms.entity.EmployeeHrmsDetail;
import com.hrms.repository.EmployeeHrmsRepository;
import com.hrms.service.EmployeeHrmsDetailService;

 
@Service
@Transactional
public class EmployeeHrmsDetailServiceImpl implements EmployeeHrmsDetailService {

	@Autowired
	private EmployeeHrmsRepository eRepo;

	@Override
	public EmployeeHrmsDetail getEmployee(Integer id){
		Optional<EmployeeHrmsDetail> EmployeeList = this.eRepo.findById(id);
		return ! EmployeeList.isPresent() ? null : EmployeeList.get();
	}
	
	@Override
	@Transactional
	public EmployeeHrmsDetail saveEmployeeHrmsDetails(Date currentDate, EmployeeHrmsDetailDto hrmsDto) {
		EmployeeHrmsDetail e2 = new EmployeeHrmsDetail();
		e2.setCreated_on(currentDate);
		e2.setDepartment(hrmsDto.getDepartment());
		e2.setDoj(hrmsDto.getDoj());
		e2.setEmail(hrmsDto.getEmail());
		e2.setSick_leaves(hrmsDto.getSick_leaves());
		e2.setUpdated_on(currentDate);
		e2.setEmp_id(hrmsDto.getEmp_id());
		return this.saveEmployeeHrmsDetails(e2);
	}

	@Transactional
	protected EmployeeHrmsDetail saveEmployeeHrmsDetails( EmployeeHrmsDetail employeeHrmsDetail) {
		return this.eRepo.save(employeeHrmsDetail);
	}

	@Override
	public EmployeeHrmsDetail getEmployeeByEmployeeId(Integer emp_id) {
		return this.eRepo.findByEmp_id(emp_id);
	}

	@Override
	public EmployeeHrmsDetail updateEmp3(Date currentDate,Integer emp_id,EmployeeHrmsDetailDto employeeHrmsDetail) {
		//Integer	empID=employeeHrmsDetail.getEmp_id();
		EmployeeHrmsDetail empDetail=eRepo.findByEmp_id(emp_id);
		empDetail.setDepartment(employeeHrmsDetail.getDepartment());
		empDetail.setDoj(employeeHrmsDetail.getDoj());
		empDetail.setEmail(employeeHrmsDetail.getEmail());
		return eRepo.save(empDetail);
	}
	
	@Transactional
	public List<EmployeeHrmsDetail> saveAllEmployeeHrmsDetails(List<EmployeeHrmsDetailDto> hrmsList) {
	    Date now = new Date();
	    
	    // Step 1: Load existing HRMS details
	    List<EmployeeHrmsDetail> existingList = eRepo.findAll();
	    Map<String, EmployeeHrmsDetail> existingMap = new HashMap<String, EmployeeHrmsDetail>();
	    for (EmployeeHrmsDetail e : existingList) {
	        // Composite key: emp_id + email
	        String key = e.getEmp_id() + "|" + e.getEmail().toLowerCase().trim();
	        if (!existingMap.containsKey(key)) {
	            existingMap.put(key, e);
	        }
	    }

	    // Process incoming HRMS DTOs
	    List<EmployeeHrmsDetail> toSave = new ArrayList<EmployeeHrmsDetail>();
	    Set<String> seenKeys = new HashSet<String>();

	    for (EmployeeHrmsDetailDto dto : hrmsList) {
	        String key = dto.getEmp_id() + "|" + dto.getEmail().toLowerCase().trim();

	        // Skip duplicates within the Excel sheet
	        if (seenKeys.contains(key)) {
	            continue;
	        }
	        seenKeys.add(key);

	        if (existingMap.containsKey(key)) {
	            // Update existing record
	            EmployeeHrmsDetail existing = existingMap.get(key);
	            existing.setDepartment(dto.getDepartment());
	            existing.setDoj(dto.getDoj());
	            existing.setSick_leaves(dto.getSick_leaves());
	            existing.setUpdated_on(now);
	            toSave.add(existing);
	        } else {
	            // Create new record
	            EmployeeHrmsDetail newEmp = new EmployeeHrmsDetail();
	            newEmp.setEmp_id(dto.getEmp_id());
	            newEmp.setEmail(dto.getEmail());
	            newEmp.setDepartment(dto.getDepartment());
	            newEmp.setDoj(dto.getDoj());
	            newEmp.setSick_leaves(dto.getSick_leaves());
	            newEmp.setCreated_on(now);
	            newEmp.setUpdated_on(now);
	            toSave.add(newEmp);
	        }
	    }

	    return eRepo.saveAll(toSave);
	}


}
