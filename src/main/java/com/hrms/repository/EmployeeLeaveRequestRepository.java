package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.EmployeeLeaveRequest;

@Repository
public interface EmployeeLeaveRequestRepository extends JpaRepository<EmployeeLeaveRequest, Integer>{
	public  List<EmployeeLeaveRequest> findAllByIdIn(List<Integer> ids);

}
