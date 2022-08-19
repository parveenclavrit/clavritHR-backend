package com.hrms.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.entity.EmployeeAttendance;

@Repository
public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Integer> {
	List<EmployeeAttendance> findAllByIdIn(List<Integer> ids);

	// EmployeeAttendance getTodayAttendance(Date todayDate);
	public List<EmployeeAttendance> getAttendanceByCreatedOnBetween(Date startDate, Date endDate);

	@Query(value = "SELECT * FROM employee_attendance WHERE created_on >= :startDate AND created_on <= :endDate AND emp_id = :empId", nativeQuery = true)
	List<EmployeeAttendance> findAllWithEmpId(Date startDate, Date endDate, int empId);

	@Query(value = "SELECT * FROM employee_attendance WHERE created_on >= :startDate AND created_on <= :endDate", nativeQuery = true)
	List<EmployeeAttendance> findAllWithCreationDateTimeBefore(Date startDate, Date endDate);

	@Query(value = "SELECT * FROM employee_attendance WHERE date(created_on) between date(:startDate) "
			+ " AND date(:endDate)", nativeQuery = true)
	List<EmployeeAttendance> findAllWithCreationDateBetweenCustom(@Param("startDate") String startDate,
			@Param("endDate") String endDate);
	
	@Query(value = "SELECT * FROM employee_attendance WHERE emp_id = :emp_id AND DATE(punch_in) = DATE(NOW())", nativeQuery = true)
	EmployeeAttendance findTodayAttendenceByEmpId(@Param("emp_id")Integer empId);

//public List<EmployeeAttendance> getAttendanceByDateAndId(Date startDate, Date endDate, int empId);
//    
//	@Query(value="SELECT * FROM employee_attendance WHERE created_on >= :startDate AND created_on <= :endDate, And empId" ,nativeQuery = true)
//	List<EmployeeAttendance> findAllWithCreationDateTimeAndId(Date startDate, Date endDate, int empId);
}
