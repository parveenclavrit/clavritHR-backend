package com.hrms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.dto.HiringDto;
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
			return ! list.isPresent()? null: list.get();
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
	
	public List<EmployeeHiringDetail> saveAllEmployeeHiring(List<HiringDto> hiringDtoList) {
        Date now = new Date();

        // Load existing hiring data
        List<EmployeeHiringDetail> existingList = hiringrepo.findAll();
        Map<String, EmployeeHiringDetail> existingMap = new HashMap<>();
        for (EmployeeHiringDetail e : existingList) {
            String key = e.getCandidate_info().toLowerCase().trim() + "|" + e.getJobOpenings().toLowerCase().trim();
            existingMap.put(key, e);
        }

        List<EmployeeHiringDetail> toSave = new ArrayList<>();
        Set<String> seenKeys = new HashSet<>();

        for (HiringDto dto : hiringDtoList) {
            String key = dto.getcandidate_info().toLowerCase().trim() + "|" + dto.getjobOpenings().toLowerCase().trim();

            // Skip duplicates within Excel
            if (seenKeys.contains(key)) continue;
            seenKeys.add(key);

            if (existingMap.containsKey(key)) {
                // Update existing record
                EmployeeHiringDetail existing = existingMap.get(key);
                existing.setStatus(dto.getStatus());
                existing.setLastEmail(dto.getlastEmail());
                toSave.add(existing);
            } else {
                // Create new entity
                EmployeeHiringDetail newEntity = new EmployeeHiringDetail();
                newEntity.setCandidate_info(dto.getcandidate_info());
                newEntity.setJobOpenings(dto.getjobOpenings());
                newEntity.setStatus(dto.getStatus());
                newEntity.setLastEmail(dto.getlastEmail());
                toSave.add(newEntity);
            }
        }

        return hiringrepo.saveAll(toSave);
    }

}
