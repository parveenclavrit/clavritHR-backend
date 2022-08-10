package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.entity.EmployeePersonalDetail;

@Repository
public interface EmployeePersonalDetailsRepository extends JpaRepository<EmployeePersonalDetail, Integer> {

	List<EmployeePersonalDetail> findAllByIdIn(List<Integer> ids);

	//@Query(value = "select t from EmployeePersonalDetail t where t.emp_id = :emp_id")
	//EmployeePersonalDetail findByEmp_Id(@Param("emp_id") Integer emp_id);

}
