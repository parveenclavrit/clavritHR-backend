package com.clavrit.utility;

import com.clavrit.dto.PeopleDto;

import java.util.List;

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
