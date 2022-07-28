package com.hrms.dto;

public class EmployeePersonalDetailsDto {
	private int id;
	private int emp_id;
	private String name;
	private String gender;
	private int age;
	private String address;
	private String other_field_1;
	private String other_field_2;
	
	public EmployeePersonalDetailsDto() {}
	
	public EmployeePersonalDetailsDto(int id, int emp_id, String name, String gender, int age, String address,
			String other_field_1, String other_field_2) {
		super();
		this.id = id;
		this.emp_id = emp_id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.address = address;
		this.other_field_1 = other_field_1;
		this.other_field_2 = other_field_2;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOther_field_1() {
		return other_field_1;
	}
	public void setOther_field_1(String other_field_1) {
		this.other_field_1 = other_field_1;
	}
	public String getOther_field_2() {
		return other_field_2;
	}
	public void setOther_field_2(String other_field_2) {
		this.other_field_2 = other_field_2;
	}

	@Override
	public String toString() {
		return "EmployeePersonalDetailsDto [id=" + id + ", emp_id=" + emp_id + ", name=" + name + ", gender=" + gender
				+ ", age=" + age + ", address=" + address + ", other_field_1=" + other_field_1 + ", other_field_2="
				+ other_field_2 + "]";
	}

	public Object getDepartment() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
