package com.hrms.controller;

import java.util.List;

import javax.validation.Valid;

import com.hrms.entity.EmployeeMaster;
import com.hrms.enums.ExceptionEnum;
import com.hrms.enums.SuccessEnum;
import com.hrms.repository.EmployeeMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
			res.setCode(ExceptionEnum.INVALID_PARAMETER.getErrorCode());
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
   @GetMapping ("/delete_employee/{emp_id}")
	public EmployeeProfileResDto deleteEmpProfile(@PathVariable("emp_id") Integer emp_id) throws Exception {
		EmployeeProfileResDto response=new EmployeeProfileResDto();
			  EProfileDataDto eProfileDataDto=profileService.deleteEmployee(emp_id);
			 if (emp_id!=null) {
				 //logger.info("campus deleted successfully");
				 response.setCode(SuccessEnum.SUCCESS_TYPE.getCode());
				 response.setMessage(SuccessEnum.SUCCESS_TYPE.getMessage());
			 }
			 else{
				 response.setCode(ExceptionEnum.DATA_NOT_FOUND.getErrorCode());
				 response.setMessage(ExceptionEnum.DATA_NOT_FOUND.getMessage());
			 }
	   return response;
   }
   @PutMapping("/update-employee/{emp_id}")
   public ResponseEntity<EmployeeProfileResDto> updateEmpDetail(@PathVariable(value = "emp_id") Integer emp_id, @RequestBody EProfileDataDto req2){
		EmployeeProfileResDto response = profileService.updateEmployeeProfile(emp_id,req2);
	   return new ResponseEntity<>(response, HttpStatus.OK);
   }
}