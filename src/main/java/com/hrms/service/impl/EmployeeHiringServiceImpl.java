package com.hrms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hrms.entity.EmployeeHiringDetail;
import com.hrms.repository.EmployeeHiringRepository;
import com.hrms.service.EmployeeHiringSevice;

@Service
public class EmployeeHiringServiceImpl implements EmployeeHiringSevice {

	@Autowired
	EmployeeHiringRepository hiringrepo;
	
	@Override
	public EmployeeHiringDetail getHiringDetails(int id) {
		Optional<EmployeeHiringDetail> list = hiringrepo.findById(id);
			return list.isEmpty()? null: list.get();
	}

	@Override
	public List<EmployeeHiringDetail> getALLEmployeeHiringDetails() {
		
			
			return hiringrepo.findAll();
		
	}

	@Override
	public void deleteEmployeeHiringDetail(int id) {
	hiringrepo.deleteById(id);
		
	}

	@Override
	public EmployeeHiringDetail saveBook(EmployeeHiringDetail hiringdetail) {
		hiringrepo.save(hiringdetail);
		return hiringdetail;
		
	}

}
