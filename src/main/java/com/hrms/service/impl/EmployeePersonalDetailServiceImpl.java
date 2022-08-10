package com.hrms.service.impl;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.dto.EmployeePersonalDetailsDto;
import com.hrms.entity.EmployeePersonalDetail;
import com.hrms.repository.EmployeePersonalDetailsRepository;
import com.hrms.service.EmployeePersonalDetailService;

@Service
public class EmployeePersonalDetailServiceImpl implements EmployeePersonalDetailService {

	@Autowired
	private EmployeePersonalDetailsRepository repo;
	
	@Override
	public EmployeePersonalDetail getEmpPersonalDetailsById(Integer id) {
		return repo.findById(id).get();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public EmployeePersonalDetail saveEmployeePersonalDetails(Date currentDate,
			EmployeePersonalDetailsDto personalDto) {
		EmployeePersonalDetail e3 = new EmployeePersonalDetail();
		e3.setName(personalDto.getName());
		e3.setEmp_id(personalDto.getEmp_id());
		e3.setCreated_on(currentDate);
		e3.setUdated_on(currentDate);
		e3.setGender(personalDto.getGender());
		e3.setAge(personalDto.getAge());
		e3.setAddress(personalDto.getAddress());
		e3.setOther_field_1(personalDto.getOther_field_1());
		e3.setOther_field_2(personalDto.getOther_field_2());
		return this.saveEmployeePersonalDetails(e3);
	}

	@Transactional
	protected EmployeePersonalDetail saveEmployeePersonalDetails(EmployeePersonalDetail empDetails) {
		return repo.save(empDetails);
	}

	@Override
	public EmployeePersonalDetail getEmployeePersonalDetailsByEmployeeId(Integer emp_id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
