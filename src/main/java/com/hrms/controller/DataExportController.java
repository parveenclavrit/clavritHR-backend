package com.hrms.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
