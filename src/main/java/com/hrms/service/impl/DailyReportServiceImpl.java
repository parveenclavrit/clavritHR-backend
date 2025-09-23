package com.hrms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.entity.DailyReport;
import com.hrms.repository.DailyReportRepository;

@Service
public class DailyReportServiceImpl {
	
    @Autowired
    private DailyReportRepository dailyReportRepository;
	
    Logger logger = LoggerFactory.getLogger(DailyReportServiceImpl.class);

    public List<DailyReport> saveAllDailyReports(List<DailyReport> reports) {

        // Load existing reports from DB
        List<DailyReport> existingList = dailyReportRepository.findAll();
        Map<String, DailyReport> existingMap = new HashMap<>();

        for (DailyReport e : existingList) {
            String key = (e.getEmail() + "|" + e.getDate()).toLowerCase().trim();
            if (!existingMap.containsKey(key)) {
                existingMap.put(key, e);
            }
        }

        // Counters
        int skippedCount = 0;
        int updatedCount = 0;
        int newCount = 0;

        // Process incoming reports
        List<DailyReport> toSave = new ArrayList<>();
        Set<String> seenKeys = new HashSet<>();

        for (DailyReport report : reports) {
            String key = (report.getEmail() + "|" + report.getDate()).toLowerCase().trim();

            // Skip duplicates in Excel batch itself
            if (seenKeys.contains(key)) {
                skippedCount++;
                continue;
            }
            seenKeys.add(key);

            if (existingMap.containsKey(key)) {
                // Update existing record
                DailyReport existing = existingMap.get(key);
                existing.setAttendance(report.getAttendance());
                existing.setTasksPerformed(report.getTasksPerformed());
                existing.setTechnicalChallenges(report.getTechnicalChallenges());
                existing.setProjectName(report.getProjectName());
                existing.setSelfLearningTopic(report.getSelfLearningTopic());
                existing.setOtherActivities(report.getOtherActivities());
                existing.setEmployeeName(report.getEmployeeName());
                toSave.add(existing);
                updatedCount++;
            } else {
                // New record
                DailyReport newReport = new DailyReport();
                newReport.setEmployeeName(report.getEmployeeName());
                newReport.setEmail(report.getEmail());
                newReport.setDate(report.getDate());
                newReport.setAttendance(report.getAttendance());
                newReport.setTasksPerformed(report.getTasksPerformed());
                newReport.setTechnicalChallenges(report.getTechnicalChallenges());
                newReport.setProjectName(report.getProjectName());
                newReport.setSelfLearningTopic(report.getSelfLearningTopic());
                newReport.setOtherActivities(report.getOtherActivities());
                toSave.add(newReport);
                newCount++;
            }
        }

        List<DailyReport> savedReports = dailyReportRepository.saveAll(toSave);

        logger.info("DailyReport Import Summary: {} new, {} updated, {} skipped (duplicates in Excel)", newCount, updatedCount, skippedCount);

        return savedReports;
    }
}