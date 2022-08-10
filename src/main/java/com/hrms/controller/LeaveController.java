package com.hrms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.dto.LeaveReqDto;
import com.hrms.service.LeaveRequestService;

@RestController
public class LeaveController {
	
	@Autowired
	private LeaveRequestService leaveService;
	
	@PostMapping("/leave-request")
	public ResponseEntity<?> login(@RequestBody LeaveReqDto leaveReqDto) {
		System.out.println(leaveReqDto.toString());
		Map<String, String> map = new HashMap<>();
		map.put("response", "success");
		leaveService.saveEmployeeLeaveRequest(new Date(), leaveReqDto);
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}
}
