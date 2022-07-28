package com.hrms.controller;

import com.hrms.entity.ClavritPeople;
import com.hrms.service.PeoplelService;
import com.hrms.service.impl.ClavritPeopleDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.dto.PeopleDto;
import com.hrms.utility.PeopleList;

import java.util.List;
@RestController
public class PeopleController {
    @Autowired
    PeoplelService peopleser;

    @GetMapping("/getPeople")
    public List<ClavritPeople> getClavritPeople() {
        return this.peopleser.getAllClavritPeple();
    }

   // @GetMapping("/getPeople")
//    public List<PeopleDto> people () {
//        PeopleList pl=new PeopleList();
//
//        List<PeopleDto> listofPeople=pl.getPeople();
//        for(PeopleDto p :listofPeople)
//        {
//            System.out.println(p.getName());
//            System.out.println(p.getAddress());
//        }
//    return listofPeople;
//    }
}
