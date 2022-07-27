package com.hrms.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.entity.EmployeeHrmsDetail;
import com.hrms.repository.EmployeeHrmsRepository;
import com.hrms.service.EmployeeHrmsDetailService;

 
@Service
@Transactional
public class EmployeeHrmsDetailServiceImpl implements EmployeeHrmsDetailService {

	@Autowired
	private EmployeeHrmsRepository eRepo;

	@Override
	public EmployeeHrmsDetail getEmployee(int id) {
		Optional<EmployeeHrmsDetail> employeeList = this.eRepo.findById(id);
		return  employeeList.isEmpty() ? null : employeeList.get();
	}

 

	@Override
	public EmployeeHrmsDetail saveEmployeeMaster(EmployeeHrmsDetail employeeHrmsDetail) {
		 return this.eRepo.save(employeeHrmsDetail);
	}


 
	 

}
