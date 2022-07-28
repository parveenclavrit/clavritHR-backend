package com.hrms.service.impl;

import com.hrms.entity.ClavritPeople;
import com.hrms.repository.ClavritpeopleRepository;
import com.hrms.service.PeoplelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
}
