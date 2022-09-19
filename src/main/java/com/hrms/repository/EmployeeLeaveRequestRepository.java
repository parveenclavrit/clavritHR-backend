package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.entity.EmployeeLeaveRequest;

@Repository
public interface EmployeeLeaveRequestRepository extends JpaRepository<EmployeeLeaveRequest, Integer> {
	public List<EmployeeLeaveRequest> findAllByIdIn(List<Integer> ids);

	@Query(nativeQuery = true, value = "SELECT * FROM employee_leave_request WHERE emp_id =:empId AND (DATE(from_date)"
			+ " BETWEEN  DATE(:fromDate) AND DATE(:toDate) "
			+ "OR DATE(to_date) BETWEEN  DATE(:fromDate) AND DATE(:toDate))")
	public List<EmployeeLeaveRequest> findAllByEmployeeIdAndDateBetween(@Param("empId") Integer empId, 
			@Param("fromDate") String fromDate, @Param("toDate")String toDate);

}
