package com.hrms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.dto.LeaveReqDto;

@RestController
public class LeaveController {
	
	@PostMapping("/leave-request")
	public ResponseEntity<?> login(@RequestBody LeaveReqDto leaveReqDto) {
		System.out.println(leaveReqDto.toString());
		Map<String, String> map = new HashMap<>();
		map.put("response", "success");
		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}
}
