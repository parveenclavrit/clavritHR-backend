package com.hrms.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.dto.LoginDto;
import com.hrms.entity.EmployeeHrmsDetail;
import com.hrms.entity.EmployeeMaster;
import com.hrms.entity.EmployeePersonalDetail;
import com.hrms.response.LoginResponse;
import com.hrms.service.EmployeeHrmsDetailService;
import com.hrms.service.EmployeeMasterService;

@RestController
public class LoginController {
	@Autowired
	EmployeeMasterService eMasterSerice;
	
	@Autowired
	EmployeeHrmsDetailService employeeHrmsService;
	
	@GetMapping("")
	public String home () {
		return "welcome home";
	}

	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginDto loginDto) {
		LoginResponse response = new LoginResponse();
		EmployeeMaster employeeMasterEntity = eMasterSerice.getEmployee(Integer.parseInt(loginDto.getId()));
		if( employeeMasterEntity.getPassword().equals(loginDto.getPassword()) ) {
			response.setCode(200);
			response.setMessage("success");
			response.setData(employeeMasterEntity);
//		}else if(loginDto.getEmailId().equalsIgnoreCase("meenakshi@clavrit.com") && loginDto.getPassword().equalsIgnoreCase("password")) {
//			response.setCode(201);
//			response.setMessage("success");
//			response.setData(loginDto);
		}else {
			response.setCode(401);
			response.setMessage("unauthorized");
		}
		return response;
	}
	
	@GetMapping("/testdata")
	public String testData() {

		EmployeeMaster e1 = new EmployeeMaster();
		e1.setActive('y');
		e1.setRole("Admin");
		e1.setType("HR");
		e1.setCreated_on(new Date());
		e1.setUpdated_on(new Date());
		
		EmployeeMaster employeeMaster = eMasterSerice.saveEmployeeMaster(e1);
		System.out.println("getting object from DB");
		employeeMaster = eMasterSerice.getEmployee(1);
		
		EmployeeHrmsDetail e2 = new EmployeeHrmsDetail();
		
		e2.setCasual_leaves(10);
		e2.setCreated_on(new Date());
		e2.setDepartment("HR");
		e2.setDoj(new Date());
		e2.setEarn_leaves(10);
		e2.setEmail("abc@gmail.com");
		e2.setSick_leaves(12);
		e2.setUpdated_on(new Date());
		e2.setEmployeeMaster(employeeMaster);
		EmployeeHrmsDetail employeeHrmsDetail = employeeHrmsService.saveEmployeeMaster(e2);
		
		System.out.println("employee :::: "+employeeMaster);
		
		EmployeePersonalDetail e3 = new EmployeePersonalDetail();

		return "Inserted Test Data in DB";
	}
}
