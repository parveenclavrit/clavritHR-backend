package com.hrms.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.hrms.entity.ClavritPeople;
import com.hrms.entity.EmployeeAttendance;
import com.hrms.entity.EmployeeHiringDetail;
import com.hrms.entity.EmployeeHrmsDetail;
import com.hrms.entity.EmployeeLeaveRequest;
import com.hrms.entity.EmployeeMaster;
import com.hrms.entity.EmployeePersonalDetail;
import com.hrms.entity.FileUpload;
import com.hrms.entity.MyInfoDetail;
import com.hrms.repository.ClavritpeopleRepository;
import com.hrms.repository.EmployeeAttendanceRepository;
import com.hrms.repository.EmployeeHiringRepository;
import com.hrms.repository.EmployeeHrmsRepository;
import com.hrms.repository.EmployeeLeaveRequestRepository;
import com.hrms.repository.EmployeeMasterRepository;
import com.hrms.repository.EmployeePersonalDetailsRepository;
import com.hrms.repository.FileUploadRepository;
import com.hrms.repository.MyInfoRepository;

@Service
public class DataExportServiceImpl {
	
	@Autowired
	private ClavritpeopleRepository clavritpeopleRepository;

	@Autowired
	private EmployeeAttendanceRepository employeeAttendanceRepository;

	@Autowired
	private EmployeeHiringRepository employeeHiringRepository;

	@Autowired
	private EmployeeHrmsRepository employeeHrmsRepository;

	@Autowired
	private EmployeeLeaveRequestRepository employeeLeaveRequestRepository;

	@Autowired
	private EmployeeMasterRepository employeeMasterRepository;

	@Autowired
	private EmployeePersonalDetailsRepository employeePersonalDetailsRepository;

	@Autowired
	private FileUploadRepository fileUploadRepository;

	@Autowired
	private MyInfoRepository myInfoRepository;

	
	public void exportAllDataToTemplate(HttpServletResponse response) {
		try {
			InputStream templateStream = new ClassPathResource("templates/ClavritHr_Record.xlsx").getInputStream();

	        Workbook workbook = new XSSFWorkbook(templateStream);
	        
	        List<EmployeePersonalDetail> employeePersonalDetails=employeePersonalDetailsRepository.findAll();
	        List<ClavritPeople> clavritPeople = clavritpeopleRepository.findAll();
	        List<EmployeeAttendance> employeeAttendance = employeeAttendanceRepository.findAll();
	        List<EmployeeHiringDetail> employeeHiring = employeeHiringRepository.findAll();
	        List<EmployeeHrmsDetail> employeeHrms = employeeHrmsRepository.findAll();
	        List<EmployeeLeaveRequest> employeeLeaveRequests = employeeLeaveRequestRepository.findAll();
	        List<EmployeeMaster> employeeMasters = employeeMasterRepository.findAll();
	        List<FileUpload> fileUploads = fileUploadRepository.findAll();
	        List<MyInfoDetail> myInfoDetails = myInfoRepository.findAll();
	        
	        populateEmployeePersonalDetailsSheet(workbook, employeePersonalDetails);
	        populateClavritPeopleSheet(workbook, clavritPeople);
	        populateEmployeeAttendanceSheet(workbook, employeeAttendance);
	        populateEmployeeHiringSheet(workbook, employeeHiring);
	        populateEmployeeHrmsSheet(workbook, employeeHrms);
	        populateEmployeeLeaveRequestsSheet(workbook, employeeLeaveRequests);
	        populateEmployeeMastersSheet(workbook, employeeMasters);
	        populateFileUploadsSheet(workbook, fileUploads);
	        populateMyInfoDetailsSheet(workbook, myInfoDetails);

	        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	        response.setHeader("Content-Disposition", "attachment; filename=\"hr_export.xlsx\"");

	        OutputStream out = response.getOutputStream();
	        workbook.write(out);
	        workbook.close();
	        out.flush();
	        out.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to export data to Excel template", e);
		}	
	}
	
	private void populateEmployeePersonalDetailsSheet(Workbook workbook, List<EmployeePersonalDetail> list) {
	    Sheet sheet = workbook.getSheet("Employee Personal Detail");
	    if (sheet == null) {
	        sheet = workbook.createSheet("Employee Personal Detail");
	    }

	    int rowNum = 1;

	    for (EmployeePersonalDetail emp : list) {
	        Row row = sheet.createRow(rowNum++);
	        int col = 0;

	        row.createCell(col++).setCellValue(emp.getId());
	        row.createCell(col++).setCellValue(emp.getEmp_id());
	        row.createCell(col++).setCellValue(emp.getName());
	        row.createCell(col++).setCellValue(emp.getGender());
	        row.createCell(col++).setCellValue(emp.getAge());
	        row.createCell(col++).setCellValue(emp.getAddress());
	        row.createCell(col++).setCellValue(emp.getOther_field_1());
	        row.createCell(col++).setCellValue(emp.getOther_field_2());

	        if (emp.getCreated_on() != null) {
	            row.createCell(col++).setCellValue(emp.getCreated_on().toString());
	        } else {
	            row.createCell(col++).setCellValue("");
	        }

	        if (emp.getUtilDate() != null) {
	            row.createCell(col++).setCellValue(emp.getUtilDate().toString());
	        } else {
	            row.createCell(col++).setCellValue("");
	        }

	        if (emp.getUdated_on() != null) {
	            row.createCell(col++).setCellValue(emp.getUdated_on().toString());
	        } else {
	            row.createCell(col++).setCellValue("");
	        }
	    }
	}


	private void populateClavritPeopleSheet(Workbook workbook, List<ClavritPeople> list) {
	    Sheet sheet = workbook.getSheet("Clavrit People");
	    if (sheet == null) {
	        sheet = workbook.createSheet("Clavrit People");
	    }

	    int rowNum = 1; // row 0 is header

	    for (ClavritPeople person : list) {
	        Row row = sheet.createRow(rowNum++);
	        int col = 0;

	        row.createCell(col++).setCellValue(person.getId()); // ID
	        row.createCell(col++).setCellValue(person.getName()); // Name
	        row.createCell(col++).setCellValue(person.getSurname()); // Surname
	        row.createCell(col++).setCellValue(person.getEmp_status()); // Emp Status
	        row.createCell(col++).setCellValue(person.getEmail()); // Email
	        row.createCell(col++).setCellValue(person.getJob_title()); // Job Title
	        row.createCell(col++).setCellValue(person.getDepartment()); // Department

	        if (person.getJoining_date() != null) {
	            row.createCell(col++).setCellValue(person.getJoining_date().toString()); // Joining Date
	        } else {
	            row.createCell(col++).setCellValue("");
	        }

	        row.createCell(col++).setCellValue(person.getAddress()); // Address
	    }
	}


	private void populateEmployeeAttendanceSheet(Workbook workbook, List<EmployeeAttendance> list) {
	    Sheet sheet = workbook.getSheet("Employee Attendance");
	    if (sheet == null) {
	        sheet = workbook.createSheet("Employee Attendance");
	    }

	    int rowNum = 1; // Start after header

	    for (EmployeeAttendance attendance : list) {
	        Row row = sheet.createRow(rowNum++);
	        int col = 0;

	        row.createCell(col++).setCellValue(attendance.getId()); // ID
	        row.createCell(col++).setCellValue(attendance.getEmpId()); // Emp ID

	        row.createCell(col++).setCellValue(
	            attendance.getPunchIn() != null ? attendance.getPunchIn().toString() : ""
	        ); // Punch In

	        row.createCell(col++).setCellValue(
	            attendance.getPunchOut() != null ? attendance.getPunchOut().toString() : ""
	        ); // Punch Out

	        row.createCell(col++).setCellValue(
	            attendance.getCreatedOn() != null ? attendance.getCreatedOn().toString() : ""
	        ); // Created On

	        row.createCell(col++).setCellValue(
	            attendance.getUdatedOn() != null ? attendance.getUdatedOn().toString() : ""
	        ); // Updated On
	    }
	}


	private void populateEmployeeHiringSheet(Workbook workbook, List<EmployeeHiringDetail> list) {
	    Sheet sheet = workbook.getSheet("Employee Hiring Detail");
	    if (sheet == null) {
	        sheet = workbook.createSheet("Employee Hiring Detail");
	    }

	    int rowNum = 1; // start after header

	    for (EmployeeHiringDetail hiring : list) {
	        Row row = sheet.createRow(rowNum++);
	        int col = 0;

	        row.createCell(col++).setCellValue(hiring.getId()); // ID
	        row.createCell(col++).setCellValue(
	            hiring.getCandidate_info() != null ? hiring.getCandidate_info() : ""
	        ); // Candidate Info
	        row.createCell(col++).setCellValue(
	            hiring.getJobOpenings() != null ? hiring.getJobOpenings() : ""
	        ); // Job Openings
	        row.createCell(col++).setCellValue(
	            hiring.getStatus() != null ? hiring.getStatus() : ""
	        ); // Status
	        row.createCell(col++).setCellValue(
	            hiring.getLastEmail() != null ? hiring.getLastEmail() : ""
	        ); // Last Email
	    }
	}


	private void populateEmployeeHrmsSheet(Workbook workbook, List<EmployeeHrmsDetail> list) {
	    Sheet sheet = workbook.getSheet("Employee Hrms Detail");
	    if (sheet == null) {
	        sheet = workbook.createSheet("Employee Hrms Detail");
	    }

	    int rowNum = 1; // start after header

	    for (EmployeeHrmsDetail hrms : list) {
	        Row row = sheet.createRow(rowNum++);
	        int col = 0;

	        row.createCell(col++).setCellValue(hrms.getId()); // ID
	        row.createCell(col++).setCellValue(
	            hrms.getEmp_id() != null ? hrms.getEmp_id() : 0
	        ); // Emp ID
	        row.createCell(col++).setCellValue(
	            hrms.getEmail() != null ? hrms.getEmail() : ""
	        ); // Email
	        row.createCell(col++).setCellValue(
	            hrms.getDepartment() != null ? hrms.getDepartment() : ""
	        ); // Department

	        // DOJ
	        if (hrms.getDoj() != null) {
	            row.createCell(col++).setCellValue(hrms.getDoj().toString());
	        } else {
	            row.createCell(col++).setCellValue("");
	        }

	        row.createCell(col++).setCellValue(hrms.getSick_leaves()); // Sick Leaves

	        // Created On
	        if (hrms.getCreated_on() != null) {
	            row.createCell(col++).setCellValue(hrms.getCreated_on().toString());
	        } else {
	            row.createCell(col++).setCellValue("");
	        }

	        // Updated On
	        if (hrms.getUpdated_on() != null) {
	            row.createCell(col++).setCellValue(hrms.getUpdated_on().toString());
	        } else {
	            row.createCell(col++).setCellValue("");
	        }
	    }
	}


	private void populateEmployeeLeaveRequestsSheet(Workbook workbook, List<EmployeeLeaveRequest> list) {
	    Sheet sheet = workbook.getSheet("Employee Leave Request");
	    if (sheet == null) {
	        sheet = workbook.createSheet("Employee Leave Request");
	    }

	    int rowNum = 1; // Start after header row

	    for (EmployeeLeaveRequest leave : list) {
	        Row row = sheet.createRow(rowNum++);
	        int col = 0;

	        row.createCell(col++).setCellValue(leave.getId() != null ? leave.getId() : 0); // ID
	        row.createCell(col++).setCellValue(leave.getEmp_id() != null ? leave.getEmp_id() : 0); // Emp ID
	        row.createCell(col++).setCellValue(leave.getNo_of_leave()); // No. of Leave
	        row.createCell(col++).setCellValue(leave.getFrom_date() != null ? leave.getFrom_date() : ""); // From Date
	        row.createCell(col++).setCellValue(leave.getTo_date() != null ? leave.getTo_date() : ""); // To Date
	        row.createCell(col++).setCellValue(leave.getLeave_type() != null ? leave.getLeave_type() : ""); // Leave Type
	        row.createCell(col++).setCellValue(leave.getMessage() != null ? leave.getMessage() : ""); // Message

	        // Created On
	        if (leave.getCreated_on() != null) {
	            row.createCell(col++).setCellValue(leave.getCreated_on().toString());
	        } else {
	            row.createCell(col++).setCellValue("");
	        }

	        // Updated On
	        if (leave.getUpdated_on() != null) {
	            row.createCell(col++).setCellValue(leave.getUpdated_on().toString());
	        } else {
	            row.createCell(col++).setCellValue("");
	        }
	    }
	}


	private void populateEmployeeMastersSheet(Workbook workbook, List<EmployeeMaster> list) {
	    Sheet sheet = workbook.getSheet("Employee Master");
	    if (sheet == null) {
	        sheet = workbook.createSheet("Employee Master");
	    }

	    int rowNum = 1; // Start after header row

	    for (EmployeeMaster master : list) {
	        Row row = sheet.createRow(rowNum++);
	        int col = 0;

	        row.createCell(col++).setCellValue(master.getId() != null ? master.getId() : 0); // ID
	        row.createCell(col++).setCellValue(master.getRole() != null ? master.getRole() : ""); // Role
	        row.createCell(col++).setCellValue(String.valueOf(master.getActive())); // Active (char)
	        row.createCell(col++).setCellValue(master.getType() != null ? master.getType() : ""); // Type
	        row.createCell(col++).setCellValue(master.getPassword() != null ? master.getPassword() : ""); // Password

	        // Created On
	        if (master.getCreated_on() != null) {
	            row.createCell(col++).setCellValue(master.getCreated_on().toString());
	        } else {
	            row.createCell(col++).setCellValue("");
	        }

	        // Updated On
	        if (master.getUpdated_on() != null) {
	            row.createCell(col++).setCellValue(master.getUpdated_on().toString());
	        } else {
	            row.createCell(col++).setCellValue("");
	        }
	    }
	}


	private void populateFileUploadsSheet(Workbook workbook, List<FileUpload> list) {
	    Sheet sheet = workbook.getSheet("File Upload");
	    if (sheet == null) {
	        sheet = workbook.createSheet("File Upload");
	    }

	    int rowNum = 1; // Start after header row

	    for (FileUpload file : list) {
	        Row row = sheet.createRow(rowNum++);
	        int col = 0;

	        row.createCell(col++).setCellValue(file.getId()); // ID
	        row.createCell(col++).setCellValue(file.getEmpId()); // Emp ID
	        row.createCell(col++).setCellValue(file.getFileName() != null ? file.getFileName() : ""); // File Name
	    }
	}


	private void populateMyInfoDetailsSheet(Workbook workbook, List<MyInfoDetail> list) {
	    Sheet sheet = workbook.getSheet("My Info Detail");
	    if (sheet == null) {
	        sheet = workbook.createSheet("My Info Detail");
	    }

	    int rowNum = 1; // Start after header row

	    for (MyInfoDetail info : list) {
	        Row row = sheet.createRow(rowNum++);
	        int col = 0;

	        row.createCell(col++).setCellValue(info.getId()); // ID
	        row.createCell(col++).setCellValue(info.getName() != null ? info.getName() : "");
	        row.createCell(col++).setCellValue(info.getAddress() != null ? info.getAddress() : "");
	        row.createCell(col++).setCellValue(info.getEmp_mail() != null ? info.getEmp_mail() : "");
	        row.createCell(col++).setCellValue(info.getPhone_num() != null ? info.getPhone_num() : "");
	        row.createCell(col++).setCellValue(info.getJoining_date() != null ? info.getJoining_date() : "");
	        row.createCell(col++).setCellValue(
	            info.getUploadedFile() != null ? info.getUploadedFile().getName() : ""
	        ); // Only filename
	    }
	}


}
