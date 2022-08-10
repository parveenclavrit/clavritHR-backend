package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.entity.EmployeeHrmsDetail;


@Repository
public interface EmployeeHrmsRepository extends JpaRepository<EmployeeHrmsDetail, Integer> {

	public  List<EmployeeHrmsDetail> findAllByIdIn(List<Integer> ids);

	//@Query(value = "select t from EmployeeHrmsDetail t where t.emp_id = :emp_id")
	//public EmployeeHrmsDetail findByEmp_id(@Param("emp_id")Integer emp_id);

}
