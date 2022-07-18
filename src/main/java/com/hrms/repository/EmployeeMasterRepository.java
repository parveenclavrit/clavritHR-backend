package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.EmployeeMaster;


@Repository
public interface EmployeeMasterRepository extends JpaRepository<EmployeeMaster, Integer> {

	public  List<EmployeeMaster> findAllByIdIn(List<Integer> ids);

}
