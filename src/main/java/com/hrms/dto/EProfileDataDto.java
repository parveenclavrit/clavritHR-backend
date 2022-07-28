package com.hrms.dto;

public class EProfileDataDto {
	private EmployeePersonalDetailsDto empPersonalDetails;
	private EmployeeHrmsDetailDto empHrmsDetails;
	private EmployeeMasterDto empMasterDetails;
	
	public EProfileDataDto() {
		super();
	}
	
	public EProfileDataDto(EmployeePersonalDetailsDto empPersonalDetails, EmployeeHrmsDetailDto empHrmsDetails,
			EmployeeMasterDto empMasterDetails) {
		super();
		this.empPersonalDetails = empPersonalDetails;
		this.empHrmsDetails = empHrmsDetails;
		this.empMasterDetails = empMasterDetails;
	}

	public EmployeePersonalDetailsDto getEmpPersonalDetails() {
		return empPersonalDetails;
	}
	public void setEmpPersonalDetails(EmployeePersonalDetailsDto empPersonalDetails) {
		this.empPersonalDetails = empPersonalDetails;
	}
	public EmployeeHrmsDetailDto getEmpHrmsDetails() {
		return empHrmsDetails;
	}
	public void setEmpHrmsDetails(EmployeeHrmsDetailDto empHrmsDetails) {
		this.empHrmsDetails = empHrmsDetails;
	}

	public EmployeeMasterDto getEmpMasterDetails() {
		return empMasterDetails;
	}

	public void setEmpMasterDetails(EmployeeMasterDto empMasterDetails) {
		this.empMasterDetails = empMasterDetails;
	}

	@Override
	public String toString() {
		return "EProfileDataDto [empPersonalDetails=" + empPersonalDetails + ", empHrmsDetails=" + empHrmsDetails
				+ ", empMasterDetails=" + empMasterDetails + "]";
	}
	
}
