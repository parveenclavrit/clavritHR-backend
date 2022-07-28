package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.dto.EProfileDataDto;
import com.hrms.dto.EmployeeProfileResDto;
import com.hrms.service.EmployeeProfileService;
import com.hrms.validators.ValidateEmpProfileCreateReq;

@RestController
public class EmployeeProfileController {
	
	@Autowired
	private EmployeeProfileService profileService;
	
	@PostMapping("/employee-profile")
	public ResponseEntity<EmployeeProfileResDto> testData(@RequestBody EProfileDataDto req) {
		
		//TODO: Validate Request
		List<String> error = ValidateEmpProfileCreateReq.validateReuqest(req);
		if(!error.isEmpty()) {
			EmployeeProfileResDto res = new EmployeeProfileResDto();
			res.setErrors(error);
			return new ResponseEntity<EmployeeProfileResDto>(res, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(profileService.createEmployeeProfile(req), HttpStatus.OK);
	}
	
}