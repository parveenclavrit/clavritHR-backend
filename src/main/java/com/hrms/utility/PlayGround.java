package com.hrms.utility;

import java.util.List;

import com.hrms.dto.PeopleDto;

public class PlayGround {
    public static void main(String[] args) {

        PeopleList pl=new PeopleList();

        List<PeopleDto> listofPeople=pl.getPeople();
        for(PeopleDto p :listofPeople)
        {
            System.out.println(p.getName());
            System.out.println(p.getAddress());
        }
    }
}
