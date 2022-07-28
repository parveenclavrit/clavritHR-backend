package com.hrms.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.dto.EProfileDataDto;
import com.hrms.dto.EmployeeMasterDto;
import com.hrms.entity.EmployeeMaster;
import com.hrms.repository.EmployeeMasterRepository;
import com.hrms.service.EmployeeMasterService;

 
@Service
public class EmployeeMasterServiceImpl implements EmployeeMasterService {

	@Autowired
	private EmployeeMasterRepository eRepo;

	@Override
	public EmployeeMaster getEmployee(int id) {
		Optional<EmployeeMaster> EmployeeList = this.eRepo.findById(id);
		return EmployeeList.isEmpty() ? null : EmployeeList.get();
	}

	@Override
	public List<EmployeeMaster> getEmployeeByList(String idListSepByComma) {
		List<Integer> listInt = Arrays.asList(idListSepByComma.split(",")).stream().map(e -> Integer.parseInt(e))
				.collect(Collectors.toList());
		return eRepo.findAllByIdIn(listInt);
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public EmployeeMaster saveEmployeeMaster(final Date currentDate, final EmployeeMasterDto req) {
		EmployeeMaster e1 = new EmployeeMaster();
		e1.setActive(req.getActive());
		e1.setRole(req.getRole());
		e1.setType(req.getType());
		e1.setCreated_on(currentDate);
		e1.setUpdated_on(currentDate);
		return this.saveEmployeeMaster(e1);
	}

	@Transactional
	protected EmployeeMaster saveEmployeeMaster(EmployeeMaster employeeMaster) {
		return this.eRepo.save(employeeMaster);
	}
}
