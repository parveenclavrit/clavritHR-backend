package com.clavrit.utility;

import com.clavrit.dto.PeopleDto;

import java.util.ArrayList;
import java.util.List;

public class PeopleList {
public List<PeopleDto> getPeople(){
    List<PeopleDto> li=new ArrayList<PeopleDto>();
    li.add(new PeopleDto("sachin","hathras","sachin@clavrit.com","8433112543","29/05/200","16/05/2022"));
    li.add(new PeopleDto("praveen","gurgaon","praveen@clavrit.com","1234567","02/05/1998","01/03/2022"));
    li.add(new PeopleDto("Sonam","merath","sonam@clavrit.com","1233777","03/05/1998","04/03/2022"));
    li.add(new PeopleDto("ujjwal","Bulandshahar","ujjwal@clavrit.com","345434567","09/05/1998","01/03/2022"));
return li;
}
}