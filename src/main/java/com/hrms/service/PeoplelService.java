package com.hrms.service;

import com.hrms.entity.ClavritPeople;

import java.util.List;

public interface PeoplelService {
    ClavritPeople getClavritPeople(int id);
    List<ClavritPeople> getAllClavritPeple();
    void deleteClavritPeople(int id);
    ClavritPeople saveBook(ClavritPeople clavritPeople);
}
