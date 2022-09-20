package com.hrms.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.dto.LeaveReqDto;
import com.hrms.entity.EmployeeLeaveRequest;
import com.hrms.response.StandardResponse;
import com.hrms.service.LeaveRequestService;

@RestController
public class LeaveController {

	@Autowired
	private LeaveRequestService leaveService;
	
	@GetMapping("{empId}/leave-details")
	public ResponseEntity<StandardResponse> getLeaveDetailsByEmployee(@PathVariable("empId") Integer empId) {
		Map<String, Long> leavesMap = new HashMap<>();
		leavesMap.put("totalLeaves", 20L);
		long leavesTaken = leaveService.getEmployeeLeavesByEmpId(empId);
		leavesMap.put("leavesLeft", leavesMap.get("totalLeaves")-leavesTaken);
		StandardResponse res = new StandardResponse(200, "Success", LocalDateTime.now(), false, leavesMap) ;
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	

	@PostMapping("/leave-request")
	public ResponseEntity<StandardResponse> leaveRequest(@RequestBody LeaveReqDto leaveReqDto) {

		EmployeeLeaveRequest res = leaveService.saveEmployeeLeaveRequest(new Date(), leaveReqDto);
		StandardResponse response = new StandardResponse(200, "Leave request submitted successfully",
				LocalDateTime.now(), false, res);
		if (null == res) {
			response.setCode(400);
			response.setError(true);
			response.setMessage("Duplicate leave request. One or more leave for the date already exists");
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
