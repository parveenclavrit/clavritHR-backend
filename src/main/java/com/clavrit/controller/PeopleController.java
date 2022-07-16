package com.clavrit.controller;

import com.clavrit.dto.PeopleDto;
import com.clavrit.utility.PeopleList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
