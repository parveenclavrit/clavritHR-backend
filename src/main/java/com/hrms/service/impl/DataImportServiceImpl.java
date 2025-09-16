package com.hrms.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.hrms.dto.EmployeeAttendanceDto;
import com.hrms.dto.EmployeeHrmsDetailDto;
import com.hrms.dto.EmployeeMasterDto;
import com.hrms.dto.EmployeePersonalDetailsDto;
import com.hrms.dto.HiringDto;
import com.hrms.dto.LeaveReqDto;
import com.hrms.dto.PeopleDto;
import com.hrms.entity.MyInfoDetail;

@Service
public class DataImportServiceImpl {
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	// ---------------- Clavrit People ----------------
	public List<PeopleDto> readClavritPeople(Sheet sheet) {
	    List<PeopleDto> peopleList = new ArrayList<>();

	    for (int i = 1; i <= sheet.getLastRowNum(); i++) { // skip header
	        Row row = sheet.getRow(i);
	        if (row == null || isRowEmpty(row)) continue;

	        PeopleDto dto = new PeopleDto();
	        dto.setName(getCellValue(row, 1));         // Name
	        dto.setSurname(getCellValue(row, 2));      // Surname
	        dto.setEmp_status(getCellValue(row, 3));   // Emp Status
	        dto.setEmail(getCellValue(row, 4));        // Email
	        dto.setJob_title(getCellValue(row, 5));    // Job Title
	        dto.setDepartment(getCellValue(row, 6));   // Department
	        dto.setJoining_date(getCellValue(row, 7)); // Joining Date
	        dto.setAddress(getCellValue(row, 8));      // Address

	        peopleList.add(dto);
	    }
	    return peopleList;
	}
	
	// ---------------- Employee Hiring ----------------
	public List<HiringDto> readEmployeeHiring(Sheet sheet) {
	    List<HiringDto> hiringList = new ArrayList<>();

	    for (int i = 1; i <= sheet.getLastRowNum(); i++) { // skip header
	        Row row = sheet.getRow(i);
	        if (row == null || isRowEmpty(row)) continue;

	        HiringDto dto = new HiringDto();
	        dto.setName(getCellValue(row, 1));        // Candidate Info
	        dto.setjobOpenings(getCellValue(row, 2)); // Job Openings
	        dto.setStatus(getCellValue(row, 3));      // Status
	        dto.setlastEmail(getCellValue(row, 4));   // Last Email

	        hiringList.add(dto);
	    }
	    return hiringList;
	}
	
	// ---------------- Employee Attendance ----------------
	public List<EmployeeAttendanceDto> readEmployeeAttendance(Sheet sheet) {
	    List<EmployeeAttendanceDto> attendanceList = new ArrayList<>();

	    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	        Row row = sheet.getRow(i);
	        if (row == null || isRowEmpty(row)) continue;

	        EmployeeAttendanceDto dto = new EmployeeAttendanceDto();

	        try {
	            dto.setEmpId(Integer.parseInt(getCellValue(row, 1))); // Emp ID

	            // Punch In & Punch Out
	            if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.NUMERIC) {
	                dto.setPunchIn(row.getCell(2).getDateCellValue());
	            }
	            if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.NUMERIC) {
	                dto.setPunchOut(row.getCell(3).getDateCellValue());
	            }

	            // Created / Updated dates: use sheet value if exists, else current timestamp
	            if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.NUMERIC) {
	                dto.setCreatedOn(row.getCell(4).getDateCellValue());
	            } else {
	                dto.setCreatedOn(new Date());
	            }

	            if (row.getCell(5) != null && row.getCell(5).getCellType() == CellType.NUMERIC) {
	                dto.setUdatedOn(row.getCell(5).getDateCellValue());
	            } else {
	                dto.setUdatedOn(new Date());
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            continue; // skip row if error occurs
	        }

	        attendanceList.add(dto);
	    }

	    return attendanceList;
	}

	// ---------------- Employee Leave Request ----------------
	public List<LeaveReqDto> readEmployeeLeaveRequest(Sheet sheet) {
	    List<LeaveReqDto> leaveList = new ArrayList<>();

	    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	        Row row = sheet.getRow(i);
	        if (row == null || isRowEmpty(row)) continue;

	        LeaveReqDto dto = new LeaveReqDto();

	        try {
	            // No of Leave
	            String leaveStr = getCellValue(row, 1);
	            if (!leaveStr.isEmpty()) {
	                dto.setNo_of_leave(Integer.parseInt(leaveStr));
	            }

	            dto.setFrom_date(getCellValue(row, 2));
	            dto.setTo_date(getCellValue(row, 3));
	            dto.setLeave_type(getCellValue(row, 4));
	            dto.setMessage(getCellValue(row, 5));

	            String empIdStr = getCellValue(row, 6);
	            if (!empIdStr.isEmpty()) {
	                dto.setEmp_id(Integer.parseInt(empIdStr));
	            }

	            // Created On / Updated On: use sheet value if exists, else current timestamp
	            if (row.getCell(7) != null && row.getCell(7).getCellType() == CellType.NUMERIC) {
	                dto.setCreated_on(row.getCell(7).getDateCellValue());
	            } else {
	                dto.setCreated_on(new Date());
	            }

	            if (row.getCell(8) != null && row.getCell(8).getCellType() == CellType.NUMERIC) {
	                dto.setUpdated_on(row.getCell(8).getDateCellValue());
	            } else {
	                dto.setUpdated_on(new Date());
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            continue; // skip invalid row
	        }

	        leaveList.add(dto);
	    }

	    return leaveList;
	}

	// ---------------- My Info Detail ----------------
	public List<MyInfoDetail> readMyInfoDetail(Sheet sheet) {
	    List<MyInfoDetail> myInfoList = new ArrayList<>();

	    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	        Row row = sheet.getRow(i);
	        if (row == null || isRowEmpty(row)) continue;

	        MyInfoDetail entity = new MyInfoDetail();

	        try {
	            entity.setName(getCellValue(row, 1));           // Name
	            entity.setAddress(getCellValue(row, 2));        // Address
	            entity.setEmp_mail(getCellValue(row, 3));       // Emp Mail
	            entity.setPhone_num(getCellValue(row, 4));      // Phone Num
	            entity.setJoining_date(getCellValue(row, 5));   // Joining Date

	            String uploadedFileName = getCellValue(row, 6); // Uploaded File
	            if (!uploadedFileName.isEmpty()) {
	                File file = new File(uploadedFileName);
	                entity.setUploadedFile(file.exists() ? file : null);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            continue; // skip invalid row
	        }

	        myInfoList.add(entity);
	    }

	    return myInfoList;
	}


	
	// ---------------- EmployeeMaster ----------------
	public List<EmployeeMasterDto> readEmployeeMasters(Sheet sheet) throws java.text.ParseException {
	    List<EmployeeMasterDto> masters = new ArrayList<>();

	    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	        Row row = sheet.getRow(i);
	        if (row == null || isRowEmpty(row)) continue;

	        EmployeeMasterDto dto = new EmployeeMasterDto();
	        dto.setId(parseInt(getCellValue(row, 0)));             // col 0: ID
	        dto.setRole(getCellValue(row, 1));                     // col 1: Role
	        String activeVal = getCellValue(row, 2);               // col 2: Active
	        dto.setActive(activeVal.isEmpty() ? null : activeVal.charAt(0));
	        dto.setType(getCellValue(row, 3));                     // col 3: Type
	        dto.setPassword(getCellValue(row, 4));                 // col 4: Password
	        dto.setCreated_on(parseDate(getCellValue(row, 5)));    // col 5: Created On
	        dto.setUpdated_on(parseDate(getCellValue(row, 6)));    // col 6: Updated On

	        masters.add(dto);
	    }
	    return masters;
	}


	// ---------------- EmployeeHrmsDetail ----------------
	public List<EmployeeHrmsDetailDto> readEmployeeHrmsDetails(Sheet sheet) throws java.text.ParseException {
	    List<EmployeeHrmsDetailDto> hrmsList = new ArrayList<>();

	    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	        Row row = sheet.getRow(i);
	        if (row == null || isRowEmpty(row)) continue;

	        EmployeeHrmsDetailDto dto = new EmployeeHrmsDetailDto();
	        dto.setId(parseInt(getCellValue(row, 0)));             // col 0: ID
	        dto.setEmail(getCellValue(row, 1));                    // col 1: Email
	        dto.setDepartment(getCellValue(row, 2));               // col 2: Department
	        dto.setDoj(parseDate(getCellValue(row, 3)));           // col 3: DOJ
	        dto.setSick_leaves(parseInt(getCellValue(row, 4)));    // col 4: Sick Leaves
	        dto.setEmp_id(parseInt(getCellValue(row, 5)));         // col 5: Emp ID
	        dto.setCreated_on(parseDate(getCellValue(row, 6)));    // col 6: Created On
	        dto.setUpdated_on(parseDate(getCellValue(row, 7)));    // col 7: Updated On

	        hrmsList.add(dto);
	    }
	    return hrmsList;
	}


	// ---------------- EmployeePersonalDetail ----------------
	public List<EmployeePersonalDetailsDto> readEmployeePersonalDetails(Sheet sheet) {
	    List<EmployeePersonalDetailsDto> personals = new ArrayList<>();

	    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	        Row row = sheet.getRow(i);
	        if (row == null || isRowEmpty(row)) continue;

	        EmployeePersonalDetailsDto dto = new EmployeePersonalDetailsDto();
	        dto.setId(parseInt(getCellValue(row, 0)));             // col 0: ID
	        dto.setEmp_id(parseInt(getCellValue(row, 1)));         // col 1: Emp ID
	        dto.setName(getCellValue(row, 2));                     // col 2: Name
	        dto.setGender(getCellValue(row, 3));                   // col 3: Gender
	        dto.setAge(parseInt(getCellValue(row, 4)));            // col 4: Age
	        dto.setAddress(getCellValue(row, 5));                  // col 5: Address
	        dto.setOther_field_1(getCellValue(row, 6));            // col 6: Other Field 1
	        dto.setOther_field_2(getCellValue(row, 7));            // col 7: Other Field 2

	        personals.add(dto);
	    }
	    return personals;
	}


    // ---------------- Utility Methods ----------------
    private String getCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        return getCellValue(cell);
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }

    private boolean isRowEmpty(Row row) {
        if (row == null) return true;
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                String value = getCellValue(cell);
                if (value != null && !value.trim().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private Date parseDate(String value) throws java.text.ParseException {
        try {
            if (value == null || value.isEmpty()) return null;
            return dateFormat.parse(value);
        } catch (ParseException e) {
            return null;
        }
    }

    private int parseInt(String value) {
        try {
            if (value == null || value.isEmpty()) return 0;
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
