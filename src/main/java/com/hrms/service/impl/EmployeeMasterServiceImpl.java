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

import com.hrms.dto.EProfileDataDto;
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
	public EmployeeMaster saveEmployeeMaster(Date currentDate,EmployeeMasterDto req) {
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
	public EmployeeMaster updateEmp(Date currentDate,Integer id,EmployeeMasterDto employeeMaster) {
		//Integer	empID=employeeMaster.getId();
	    EmployeeMaster empDetail=eRepo.findById(id).get();
	    empDetail.setRole(employeeMaster.getRole());
	    empDetail.setActive(employeeMaster.getActive());
	    return eRepo.save(empDetail);

	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<EmployeeMaster> saveAllEmployeeMasters(List<EmployeeMasterDto> masters) {
	    if (masters == null || masters.isEmpty()) {
	        return new ArrayList<EmployeeMaster>();
	    }

	    Date now = new Date();
	    List<EmployeeMaster> existingMasters = eRepo.findAll();

	    // Create a map manually: key = role|type
	    Map<String, EmployeeMaster> existingMap = new HashMap<String, EmployeeMaster>();
	    for (EmployeeMaster em : existingMasters) {
	        String key = em.getRole() + "|" + em.getType() + "|" + em.getPassword();
	        if (!existingMap.containsKey(key)) {
	            existingMap.put(key, em);
	        }
	    }

	    Set<String> seenKeys = new HashSet<String>();
	    List<EmployeeMaster> toSave = new ArrayList<EmployeeMaster>();

	    for (EmployeeMasterDto dto : masters) {
	    	String key = dto.getRole() + "|" + dto.getType() + "|" + dto.getPassword();

	        if (seenKeys.contains(key)) {
	            continue; // skip duplicate in Excel
	        }
	        seenKeys.add(key);

	        if (existingMap.containsKey(key)) {
	            // Update existing
	            EmployeeMaster existing = existingMap.get(key);
	            existing.setRole(dto.getRole());
	            existing.setType(dto.getType());
	            existing.setActive(dto.getActive());
	            existing.setPassword(dto.getPassword());
	            existing.setUpdated_on(now);
	            toSave.add(existing);
	        } else {
	            // Insert new
	            EmployeeMaster eM = new EmployeeMaster();
	            eM.setRole(dto.getRole());
	            eM.setType(dto.getType());
	            eM.setActive(dto.getActive());
	            eM.setPassword(dto.getPassword());
	            eM.setCreated_on(now);
	            eM.setUpdated_on(now);
	            toSave.add(eM);
	        }
	    }

	    return eRepo.saveAll(toSave);
	}


}
