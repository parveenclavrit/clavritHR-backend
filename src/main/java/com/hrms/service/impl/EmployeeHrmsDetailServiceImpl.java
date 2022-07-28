package com.hrms.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.dto.EmployeeHrmsDetailDto;
import com.hrms.entity.EmployeeHrmsDetail;
import com.hrms.repository.EmployeeHrmsRepository;
import com.hrms.service.EmployeeHrmsDetailService;

 
@Service
public class EmployeeHrmsDetailServiceImpl implements EmployeeHrmsDetailService {

	@Autowired
	private EmployeeHrmsRepository eRepo;

	@Override
	public EmployeeHrmsDetail getEmployee(int id) {
		Optional<EmployeeHrmsDetail> employeeList = this.eRepo.findById(id);
		return !  employeeList.isPresent() ? null : employeeList.get();
	}

	@Override
	@Transactional
	public EmployeeHrmsDetail saveEmployeeHrmsDetails(Date currentDate, EmployeeHrmsDetailDto hrmsDto) {
		EmployeeHrmsDetail e2 = new EmployeeHrmsDetail();
		e2.setCasual_leaves(hrmsDto.getCasual_leaves());
		e2.setCreated_on(currentDate);
		e2.setDepartment(hrmsDto.getDepartment());
		e2.setDoj(hrmsDto.getDoj());
		e2.setEarn_leaves(hrmsDto.getEarn_leaves());
		e2.setEmail(hrmsDto.getEmail());
		e2.setSick_leaves(hrmsDto.getSick_leaves());
		e2.setUpdated_on(currentDate);
		return this.saveEmployeeHrmsDetails(e2);
	}

	@Transactional
	protected EmployeeHrmsDetail saveEmployeeHrmsDetails(EmployeeHrmsDetail employeeHrmsDetail) {
		return this.eRepo.save(employeeHrmsDetail);
	}

 
	 

}
