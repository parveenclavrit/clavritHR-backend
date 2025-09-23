package com.hrms.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.service.impl.DataExportServiceImpl;

@RestController
@RequestMapping("/api/export")
public class DataExportController {
	
	@Autowired
	DataExportServiceImpl dataExportServiceImpl;

	
	@GetMapping
	public void exportExcel(HttpServletResponse response) {
		dataExportServiceImpl.exportAllDataToTemplate(response);
	}
	
	
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	@GetMapping("/dailyReports/{email}")
    public void downloadReportsByEmail(@PathVariable String email, HttpServletResponse response) {
        try {
            dataExportServiceImpl.exportDailyReportsByEmail(email, response);
        } catch (RuntimeException ex) {
            response.setContentType("text/plain");
            try {
                response.getWriter().write("No daily reports found for email: " + email);
            } catch (IOException e) {
                throw new RuntimeException("Failed to write response", e);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Unexpected error while exporting reports", ex);
        }
    }

}
