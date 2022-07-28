package com.hrms.repository;

import com.hrms.entity.ClavritPeople;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClavritpeopleRepository extends JpaRepository<ClavritPeople,Integer> {

}
