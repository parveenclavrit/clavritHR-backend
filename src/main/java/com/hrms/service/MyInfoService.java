package com.hrms.service;
import java.util.List;

import com.hrms.entity.MyInfoDetail;



public interface MyInfoService {
	 MyInfoDetail getMyInfoDetail(int id);
	    List<MyInfoDetail> getAllMyInfo();
	    void deleteMyInfodetail(int id);
	    MyInfoDetail saveMyInfo(MyInfoDetail myinfodetail);
}
