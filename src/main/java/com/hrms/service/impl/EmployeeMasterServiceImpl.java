package com.hrms.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return ! EmployeeList.isPresent() ? null : EmployeeList.get();
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
		EmployeeMaster eM = new EmployeeMaster();
		eM.setActive(req.getActive());
		eM.setRole(req.getRole());
		eM.setType(req.getType());
		eM.setCreated_on(currentDate);
		eM.setUpdated_on(currentDate);
		return this.eRepo.save(eM);
	}

	@Override
	public List<EmployeeMaster> getAllEmployee() {
		return eRepo.findAll();
	}

	@Override
	public void EmployeedeleteById(Integer id) {
		eRepo.deleteById(id);
	}

	@Override
	public EmployeeMaster updateEmp(EmployeeMaster employeeMaster) {
	Integer	empID=employeeMaster.getId();
	    EmployeeMaster empDetail=eRepo.findById(empID).get();
	    empDetail.setRole(employeeMaster.getRole());
	    empDetail.setActive(employeeMaster.getActive());
	    return eRepo.save(empDetail);

	}

}
