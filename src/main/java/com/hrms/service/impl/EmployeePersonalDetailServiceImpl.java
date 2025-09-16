package com.hrms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.dto.EmployeePersonalDetailsDto;
import com.hrms.entity.EmployeeMaster;
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
		return repo.findByEmp_Id(emp_id);
	}

	@Override
	public Map<Integer, String> getAllEmployeeNamesByIds(List<Integer> empIds) {
		List<EmployeePersonalDetail> list = repo.findAllByEmpIdIn(empIds);
		Map<Integer, String> nameMap = list.stream()
				.collect(Collectors.toMap(EmployeePersonalDetail::getEmp_id, EmployeePersonalDetail::getName));
		return nameMap;
	}

	@Override
	public EmployeePersonalDetail updateEmp2(Date currentDate,Integer emp_id,EmployeePersonalDetailsDto employeePersonalDetail) {
		//Integer	empID=employeePersonalDetail.getEmp_id();
		EmployeePersonalDetail emp2=repo.findByEmp_Id(emp_id);
		emp2.setName(employeePersonalDetail.getName());
		emp2.setAddress(employeePersonalDetail.getAddress());
		return repo.save(emp2);
	}
	
	@Transactional
	public List<EmployeePersonalDetail> saveAllEmployeePersonalDetails(List<EmployeePersonalDetailsDto> personalList) {
	    Date now = new Date();
	    
	    // Load existing personal details
	    List<EmployeePersonalDetail> existingList = repo.findAll();
	    Map<String, EmployeePersonalDetail> existingMap = new HashMap<String, EmployeePersonalDetail>();
	    for (EmployeePersonalDetail e : existingList) {
	        String key = e.getEmp_id() + "|" + e.getName().toLowerCase().trim() + "|" + e.getGender().toLowerCase().trim();
	        if (!existingMap.containsKey(key)) {
	            existingMap.put(key, e);
	        }
	    }

	    //Process incoming DTOs
	    List<EmployeePersonalDetail> toSave = new ArrayList<EmployeePersonalDetail>();
	    Set<String> seenKeys = new HashSet<String>();

	    for (EmployeePersonalDetailsDto dto : personalList) {
	        String key = dto.getEmp_id() + "|" + dto.getName().toLowerCase().trim() + "|" + dto.getGender().toLowerCase().trim();

	        // Skip duplicates within the Excel sheet
	        if (seenKeys.contains(key)) {
	            continue;
	        }
	        seenKeys.add(key);

	        if (existingMap.containsKey(key)) {
	            // Update existing record
	            EmployeePersonalDetail existing = existingMap.get(key);
	            existing.setAddress(dto.getAddress());
	            existing.setAge(dto.getAge());
	            existing.setOther_field_1(dto.getOther_field_1());
	            existing.setOther_field_2(dto.getOther_field_2());
	            existing.setUdated_on(now);
	            toSave.add(existing);
	        } else {
	            // Create new record
	            EmployeePersonalDetail newEmp = new EmployeePersonalDetail();
	            newEmp.setEmp_id(dto.getEmp_id());
	            newEmp.setName(dto.getName());
	            newEmp.setGender(dto.getGender());
	            newEmp.setAge(dto.getAge());
	            newEmp.setAddress(dto.getAddress());
	            newEmp.setOther_field_1(dto.getOther_field_1());
	            newEmp.setOther_field_2(dto.getOther_field_2());
	            newEmp.setCreated_on(now);
	            newEmp.setUdated_on(now);
	            toSave.add(newEmp);
	        }
	    }

	    // Save all records
	    return repo.saveAll(toSave);
	}


}
