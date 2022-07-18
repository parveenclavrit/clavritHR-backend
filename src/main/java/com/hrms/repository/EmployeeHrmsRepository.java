package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.EmployeeHrmsDetail;


@Repository
public interface EmployeeHrmsRepository extends JpaRepository<EmployeeHrmsDetail, Integer> {

	public  List<EmployeeHrmsDetail> findAllByIdIn(List<Integer> ids);

}
