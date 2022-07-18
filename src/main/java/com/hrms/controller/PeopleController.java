package com.hrms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.dto.PeopleDto;
import com.hrms.utility.PeopleList;

import java.util.List;
@RestController
public class PeopleController {
    @GetMapping("/getPeople")
    public List<PeopleDto> people () {
        PeopleList pl=new PeopleList();

        List<PeopleDto> listofPeople=pl.getPeople();
        for(PeopleDto p :listofPeople)
        {
            System.out.println(p.getName());
            System.out.println(p.getAddress());
        }
    return listofPeople;
    }
}
