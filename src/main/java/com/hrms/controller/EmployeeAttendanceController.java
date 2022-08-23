package com.hrms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrms.dto.EmployeeAttendanceDto;
import com.hrms.dto.EmployeeAttendenceResDto;
import com.hrms.dto.EmployeeHrmsDetailDto;
import com.hrms.dto.EmployeeMasterDto;
import com.hrms.dto.EmployeePersonalDetailsDto;
import com.hrms.entity.EmployeeAttendance;
import com.hrms.response.EmployeeAttendanceResponse;
import com.hrms.service.EmployeeAttendanceService;
import com.hrms.service.EmployeeMasterService;
import com.hrms.service.EmployeePersonalDetailService;

@RestController
public class EmployeeAttendanceController {

	@Autowired
	public EmployeeAttendanceService empAttendanceSer;

	@Autowired
	EmployeeMasterService employeeMasterSer;

	@Autowired
	private EmployeePersonalDetailService personalDetailsSer;

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

	@GetMapping("/attendance/{empId}/{startDate}/{endDate}")
	public EmployeeAttendanceResponse getEmAttendanceBetweenTime(
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			@PathVariable(value = "empId") int empId) {
		System.out.println("emp Id " + empId);
		EmployeeAttendanceResponse response = new EmployeeAttendanceResponse();
		List<EmployeeAttendance> attendanceEntityList = empAttendanceSer.getEmAttendanceBetweenDate(startDate, endDate,
				empId);
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

	@GetMapping("/attendance/{startDate}/{endDate}")
	public ResponseEntity<EmployeeAttendanceResponse> getEmAttendanceBetweenTimeAndDate(@PathVariable String startDate,
			@PathVariable String endDate, @RequestParam(value = "empId", required = false) Integer empId) {
		EmployeeAttendanceResponse response = new EmployeeAttendanceResponse();
		List<EmployeeAttendance> attendanceEntityList = empAttendanceSer.getEmAttendanceBetweenDateAndTime(startDate,
				endDate, empId);

		if (attendanceEntityList != null && !attendanceEntityList.isEmpty()) {
			List<Integer> empIds = attendanceEntityList.stream().map(emp -> emp.getEmpId()).distinct()
					.collect(Collectors.toList());
			final Map<Integer, String> empNames = personalDetailsSer.getAllEmployeeNamesByIds(empIds);
			final List<EmployeeAttendenceResDto> resp = new ArrayList<>();
			final ObjectMapper mapper = new ObjectMapper();
			attendanceEntityList.stream().forEach(attendence -> {
				try {
					EmployeeAttendenceResDto dto = mapper.readValue(mapper.writeValueAsString(attendence),
							EmployeeAttendenceResDto.class);
					dto.setEmpName(empNames.get(attendence.getEmpId()));
					resp.add(dto);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			response.setCode(200);
			response.setMessage("success");
			response.setData(resp);
		} else {
			response.setCode(HttpStatus.NO_CONTENT.value());
			response.setMessage("No Content found");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/attendance/{empId}")
	public ResponseEntity<?> upload(@PathVariable("empId") Integer empId) throws Exception {
		EmployeeAttendance empAttendence = empAttendanceSer.findTodayAttendenceByEmpId(empId);
		EmployeeAttendanceResponse response = new EmployeeAttendanceResponse();
		if (null != empAttendence) {
			final ObjectMapper mapper = new ObjectMapper();
			EmployeeAttendanceDto dto = mapper.readValue(mapper.writeValueAsString(empAttendence),
					EmployeeAttendanceDto.class);
			response.setCode(200);
			response.setMessage("success");
			response.setData(dto);
		} else {
			response.setCode(HttpStatus.NO_CONTENT.value());
			response.setMessage("No Content found");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
