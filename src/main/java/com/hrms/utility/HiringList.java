package com.hrms.utility;

import java.util.ArrayList;
import java.util.List;

import com.hrms.dto.HiringDto;

public class HiringList {
public List<HiringDto> gethiring(){
    List<HiringDto> l=new ArrayList<HiringDto>();
    l.add(new HiringDto("Sonam", "Software Engineer", "Offer Sent", "sonam123@gmail.com"));
    l.add(new HiringDto("praveen", "Developer", "Pending", "parveen@gmail.com"));
    l.add(new HiringDto("rahul", "frontend Engineer", "Offer Sent", "rahul@gmail.com"));
    l.add(new HiringDto("puja", "java Developer", "Pending", "puja1@gmail.com"));

return l;
}
}