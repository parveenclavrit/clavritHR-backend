package com.clavrit.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clavrit.dto.LeaveReqDto;

@RestController
public class LeaveController {
	
	@PostMapping("/leave-request")
	public String login(@RequestBody LeaveReqDto leaveReqDto) {
		System.out.println(leaveReqDto.toString());
		return "Success";
	}
}
