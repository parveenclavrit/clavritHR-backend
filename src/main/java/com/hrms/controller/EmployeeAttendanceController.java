package com.hrms.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.entity.EmployeeAttendance;
import com.hrms.response.EmployeeAttendanceResponse;
import com.hrms.service.EmployeeAttendanceService;
import com.hrms.service.EmployeeMasterService;

@RestController
public class EmployeeAttendanceController {

	@Autowired
	public EmployeeAttendanceService empAttendanceSer;

	@Autowired
	EmployeeMasterService employeeMasterSer;

	@GetMapping("/attendance")
	public List<EmployeeAttendance> getAttendances() {
         String str = "1,4,119";
		return this.empAttendanceSer.getEmployeeAttendanceByList(str);
	}

	@GetMapping("/attendance/{Id}")
	public EmployeeAttendance getAttendance(@PathVariable String Id) {

		return this.empAttendanceSer.getEmployeeAttendance(Integer.parseInt(Id));
	}

	@PostMapping("/attendance")
	public EmployeeAttendanceResponse saveAttendance(@RequestBody EmployeeAttendance employeeAttendance) {
		EmployeeAttendanceResponse response = new EmployeeAttendanceResponse();
		EmployeeAttendance attendanceEntity = empAttendanceSer.saveEmployeeAttendance(employeeAttendance);
		if (attendanceEntity != null) {

			response.setCode(200);
			response.setMessage("success");
			response.setData(attendanceEntity);
		} else {
			response.setCode(401);
			response.setMessage("unauthorized");
		}
		return response;
	}

	@GetMapping("/attendance/{startDate}/{endDate}")
	public EmployeeAttendanceResponse getEmAttendanceBetweenTime(
			@PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, @RequestParam(value="empId", defaultValue = "0") int empId) {
		System.out.println("emp Id " + empId);
		EmployeeAttendanceResponse response = new EmployeeAttendanceResponse();
		List<EmployeeAttendance> attendanceEntityList = empAttendanceSer.getEmAttendanceBetweenDate(startDate, endDate);
		if (attendanceEntityList != null) {

			response.setCode(200);
			response.setMessage("success");
			response.setData(attendanceEntityList);
		} else {
			response.setCode(401);
			response.setMessage("unauthorized");
		}
		return response;
	}

	@PutMapping("/attendance/update")
	public EmployeeAttendanceResponse updateAttendance(@RequestBody EmployeeAttendance employeeAttendance) {
		EmployeeAttendanceResponse response = new EmployeeAttendanceResponse();
		EmployeeAttendance attendanceEntity = empAttendanceSer.updateEmployeeAttendance(employeeAttendance);
		if (attendanceEntity != null) {

			response.setCode(200);
			response.setMessage("success");
			response.setData(attendanceEntity);
		} else {
			response.setCode(401);
			response.setMessage("unauthorized");
		}
		return response;
	}

}
