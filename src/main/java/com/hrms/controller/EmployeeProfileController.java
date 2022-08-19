package com.hrms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.dto.EProfileDataDto;
import com.hrms.response.EmployeeProfileResDto;
import com.hrms.service.EmployeeProfileService;
import com.hrms.validators.ValidateEmpProfileCreateReq;

@RestController
@Validated
public class EmployeeProfileController {
	
	@Autowired
	private EmployeeProfileService profileService;
	
	@PostMapping("/employee-profile")
	public ResponseEntity<EmployeeProfileResDto> saveEmployeeProfile(@Valid @RequestBody EProfileDataDto req) {
		
		//TODO: Validate Request
		List<String> error = ValidateEmpProfileCreateReq.validateRequest(req);
		if(!error.isEmpty()) {
			EmployeeProfileResDto res = new EmployeeProfileResDto();
			res.setCode(400);
			res.setError(true);
			res.setMessage(String.join(",", error));
			return new ResponseEntity<EmployeeProfileResDto>(res, HttpStatus.BAD_REQUEST);
		}
		
		EmployeeProfileResDto response = profileService.createEmployeeProfile(req);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/employee-profile/{emp_id}")
	  public ResponseEntity<EProfileDataDto> getEmployeeProfile(@PathVariable("emp_id") Integer emp_id) throws Exception {
		EProfileDataDto response = profileService.getEmployeeProfile(emp_id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/employee-profile")
	  public ResponseEntity<List<EProfileDataDto>> getAllEmployeeProfile() throws Exception {
		List<EProfileDataDto> response = profileService.getAllEmployeeProfile();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}