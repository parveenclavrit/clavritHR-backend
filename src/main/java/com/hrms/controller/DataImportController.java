package com.hrms.controller;

import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hrms.dto.EmployeeAttendanceDto;
import com.hrms.dto.EmployeeHrmsDetailDto;
import com.hrms.dto.EmployeeMasterDto;
import com.hrms.dto.EmployeePersonalDetailsDto;
import com.hrms.dto.HiringDto;
import com.hrms.dto.LeaveReqDto;
import com.hrms.dto.PeopleDto;
import com.hrms.entity.DailyReport;
import com.hrms.entity.MyInfoDetail;
import com.hrms.service.impl.ClavritPeopleDetailServiceImpl;
import com.hrms.service.impl.DailyReportServiceImpl;
import com.hrms.service.impl.DataImportServiceImpl;
import com.hrms.service.impl.EmployeeAttendanceImpl;
import com.hrms.service.impl.EmployeeHiringServiceImpl;
import com.hrms.service.impl.EmployeeHrmsDetailServiceImpl;
import com.hrms.service.impl.EmployeeMasterServiceImpl;
import com.hrms.service.impl.EmployeePersonalDetailServiceImpl;
import com.hrms.service.impl.EmployeeProfileServiceImpl;
import com.hrms.service.impl.LeaveRequestServiceImp;
import com.hrms.service.impl.MyInfoServiceImpl;

@RestController
@RequestMapping("/api/import")
public class DataImportController {
	
	@Autowired
	ClavritPeopleDetailServiceImpl clavritPeopleDetailServiceImpl;
	
	@Autowired
	EmployeeAttendanceImpl employeeAttendanceImpl;
	
	@Autowired
	EmployeeHiringServiceImpl employeeHiringServiceImpl;
	
	@Autowired
	EmployeeHrmsDetailServiceImpl employeeHrmsDetailServiceImpl;
	
	@Autowired
	EmployeeMasterServiceImpl employeeMasterServiceImpl;
	
	@Autowired
	EmployeePersonalDetailServiceImpl employeePersonalDetailServiceImpl;
	
	@Autowired
	EmployeeProfileServiceImpl employeeProfileServiceImpl;
	
	@Autowired
	LeaveRequestServiceImp leaveRequestServiceImp;
	
	@Autowired
	MyInfoServiceImpl myInfoServiceImpl;
	
	@Autowired
	DataImportServiceImpl dataImportServiceImpl;

	@PostMapping("/upload")
	public ResponseEntity<Map<String, Object>> uploadExcelAndSaveToDb(@RequestParam("file") MultipartFile file) {
		Map<String, Object> resultMap = new LinkedHashMap<>();
		try (InputStream inputStream = file.getInputStream(); Workbook workbook = WorkbookFactory.create(inputStream)) {

			Sheet clavritPeopleSheet = workbook.getSheet("Clavrit People");
			Sheet employeeAttendance = workbook.getSheet("Employee Attendance");
			Sheet employeeHiring = workbook.getSheet("Employee Hiring Detail");
			Sheet employeeHrmsDetail = workbook.getSheet("Employee Hrms Detail");
			Sheet employeeMaster = workbook.getSheet("Employee Master");
			Sheet employeePersonalDetail = workbook.getSheet("Employee Personal Detail");
			Sheet leaveRequest = workbook.getSheet("Employee Leave Request");
			Sheet myInfo = workbook.getSheet("My Info Detail");
			
			// -------------------- Clavrit People --------------------
	        if (clavritPeopleSheet != null) {
	            List<PeopleDto> peopleList = dataImportServiceImpl.readClavritPeople(clavritPeopleSheet);
	            resultMap.put("clavritPeople", peopleList);
	            clavritPeopleDetailServiceImpl.saveAllClavritPeople(peopleList);
	        }
	        
	        // ---------------- Employee Attendance --------------------
            if (employeeAttendance != null) {
                List<EmployeeAttendanceDto> attendanceList = dataImportServiceImpl.readEmployeeAttendance(employeeAttendance);
                resultMap.put("employeeAttendance", attendanceList);
                employeeAttendanceImpl.saveAllEmployeeAttendance(attendanceList);
            }

            // ---------------- Employee Hiring --------------------
            if (employeeHiring != null) {
                List<HiringDto> hiringList = dataImportServiceImpl.readEmployeeHiring(employeeHiring);
                resultMap.put("employeeHiring", hiringList);
                employeeHiringServiceImpl.saveAllEmployeeHiring(hiringList);
            }
            
            // ---------------- Employee Leave Request --------------------
            if (leaveRequest != null) {
                List<LeaveReqDto> leaveList = dataImportServiceImpl.readEmployeeLeaveRequest(leaveRequest);
                resultMap.put("leaveRequests", leaveList);
                leaveRequestServiceImp.saveAllLeaveRequests(leaveList);
            }

            // ---------------- My Info Detail --------------------
            if (myInfo != null) {
                List<MyInfoDetail> myInfoList = dataImportServiceImpl.readMyInfoDetail(myInfo);
                resultMap.put("myInfoDetails", myInfoList);
                myInfoServiceImpl.saveAllMyInfoDetails(myInfoList);
            }
			
			// ---------------- Employee Master ----------------
	        if (employeeMaster != null) {
	            List<EmployeeMasterDto> masters = dataImportServiceImpl.readEmployeeMasters(employeeMaster);
	            resultMap.put("employeeMasters", masters);
	            employeeMasterServiceImpl.saveAllEmployeeMasters(masters);
	        }

	        // ---------------- Employee HRMS Detail ----------------
	        if (employeeHrmsDetail != null) {
	            List<EmployeeHrmsDetailDto> hrmsDetails = dataImportServiceImpl.readEmployeeHrmsDetails(employeeHrmsDetail);
	            resultMap.put("employeeHrmsDetails", hrmsDetails);
	            employeeHrmsDetailServiceImpl.saveAllEmployeeHrmsDetails(hrmsDetails);
	        }

	        // ---------------- Employee Personal Detail ----------------
	        if (employeePersonalDetail != null) {
	            List<EmployeePersonalDetailsDto> personals = dataImportServiceImpl.readEmployeePersonalDetails(employeePersonalDetail);
	            resultMap.put("employeePersonalDetails", personals);
	            employeePersonalDetailServiceImpl.saveAllEmployeePersonalDetails(personals);
	        }


			return ResponseEntity.ok(resultMap);

		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(Collections.singletonMap("error", e.getMessage()));
		}
	}
	
	@Autowired
	private DailyReportServiceImpl dailyReportServiceImpl;
	
	@PostMapping("/upload/dailyReports")
    public ResponseEntity<Map<String, Object>> uploadDailyReports(@RequestParam("file") MultipartFile file) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        try (InputStream inputStream = file.getInputStream(); Workbook workbook = WorkbookFactory.create(inputStream)) {

            List<DailyReport> reports = dataImportServiceImpl.readDailyReports(workbook);
            resultMap.put("dailyReports", reports);

            if (!reports.isEmpty()) {
                dailyReportServiceImpl.saveAllDailyReports(reports);
            }

            return ResponseEntity.ok(resultMap);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

}
