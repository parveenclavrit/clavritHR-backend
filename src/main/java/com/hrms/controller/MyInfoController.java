package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.entity.MyInfoDetail;
import com.hrms.service.MyInfoService;
@RestController
public class MyInfoController {
	@Autowired
	MyInfoService myinfoser;

	 @GetMapping("/getMyInfo")
	    public List<MyInfoDetail> getMyInfoDetail() {
	        return this.myinfoser.getAllMyInfo();
}
}
