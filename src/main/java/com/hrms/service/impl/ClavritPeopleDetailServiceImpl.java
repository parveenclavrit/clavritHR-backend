package com.hrms.service.impl;

import com.hrms.dto.PeopleDto;
import com.hrms.entity.ClavritPeople;
import com.hrms.repository.ClavritpeopleRepository;
import com.hrms.service.PeoplelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
@Service
public class ClavritPeopleDetailServiceImpl implements PeoplelService {
    @Autowired
    private ClavritpeopleRepository peoplerpo;

    @Override
    public ClavritPeople getClavritPeople(int id) {
        Optional<ClavritPeople> list=peoplerpo.findById(id);
        return ! list.isPresent() ? null: list.get();
    }

    @Override
    public List<ClavritPeople> getAllClavritPeple() {
        return peoplerpo.findAll();
    }

    @Override
    public void deleteClavritPeople(int id) {
      peoplerpo.deleteById(id);
    }

    @Override
    public ClavritPeople saveBook(ClavritPeople clavritPeople) {
        peoplerpo.save(clavritPeople);
        return clavritPeople;
    }
    
 // ------------------ Bulk save for Excel import ------------------
    
    public List<ClavritPeople> saveAllClavritPeople(List<PeopleDto> peopleList) {
        Date now = new Date();

        // Load existing people
        List<ClavritPeople> existingList = peoplerpo.findAll();
        Map<String, ClavritPeople> existingMap = new HashMap<>();
        for (ClavritPeople e : existingList) {
            String key = (e.getEmail() + "|" + e.getName()).toLowerCase().trim();
            if (!existingMap.containsKey(key)) {
                existingMap.put(key, e);
            }
        }

        // Process incoming DTOs
        List<ClavritPeople> toSave = new ArrayList<>();
        Set<String> seenKeys = new HashSet<>();

        for (PeopleDto dto : peopleList) {
            String key = (dto.getEmail() + "|" + dto.getName()).toLowerCase().trim();

            // Skip duplicates in Excel
            if (seenKeys.contains(key)) continue;
            seenKeys.add(key);

            if (existingMap.containsKey(key)) {
                // Update existing record
                ClavritPeople existing = existingMap.get(key);
                existing.setName(dto.getName());
                existing.setSurname(dto.getSurname());
                existing.setEmp_status(dto.getEmp_status());
                existing.setJob_title(dto.getJob_title());
                existing.setDepartment(dto.getDepartment());
                existing.setJoining_date(dto.getJoining_date());
                existing.setAddress(dto.getAddress());
                toSave.add(existing);
            } else {
                // Create new record
                ClavritPeople newPerson = new ClavritPeople();
                newPerson.setName(dto.getName());
                newPerson.setSurname(dto.getSurname());
                newPerson.setEmp_status(dto.getEmp_status());
                newPerson.setEmail(dto.getEmail());
                newPerson.setJob_title(dto.getJob_title());
                newPerson.setDepartment(dto.getDepartment());
                newPerson.setJoining_date(dto.getJoining_date());
                newPerson.setAddress(dto.getAddress());
                toSave.add(newPerson);
            }
        }

        // Save all at once
        return peoplerpo.saveAll(toSave);
    }
}
