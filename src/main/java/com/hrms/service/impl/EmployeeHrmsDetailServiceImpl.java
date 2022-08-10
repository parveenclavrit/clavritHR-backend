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
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public EmployeeHrmsDetail getEmployeeByEmployeeId(Integer emp_id) {
		return this.eRepo.findByEmp_id(emp_id);
	}**/

}
