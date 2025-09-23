package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.DailyReport;

public interface DailyReportRepository extends JpaRepository<DailyReport, Long> {

	List<DailyReport> findByEmailOrderByDateAsc(String email);

}
