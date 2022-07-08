package com.clavrit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clavrit.dto.LoginDto;
import com.clavrit.response.LoginResponse;

@RestController
public class LoginController {
	
	@GetMapping("")
	public String home () {
		return "welcome home";
	}

	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginDto loginDto) {
		LoginResponse response = new LoginResponse();
		if(loginDto.getEmailId().equalsIgnoreCase("parveen@clavrit.com") && loginDto.getPassword().equalsIgnoreCase("password")) {
			response.setCode(200);
			response.setMessage("success");
			response.setData(loginDto);
		}else {
			response.setCode(401);
			response.setMessage("unauthorized");
		}
		return response;
	}
}
