package com.hrms.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hrms.entity.EmployeeHiringDetail;
import com.hrms.entity.MyInfoDetail;
import com.hrms.service.MyInfoService;

@RestController
public class MyInfoController {
	@Autowired
	MyInfoService myinfoser;

	@Value("${file.upload-dir}")
	String FILE_DIRECTORY;

	@GetMapping("/getMyInfo/{Id}")
	   public MyInfoDetail getDetail(@PathVariable String Id) {
		   
		   return this.myinfoser.getMyInfoDetail(Integer.parseInt(Id));}
	

	@PostMapping(value = "/uploadFile", consumes = "multipart/form-data")
	public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		File myFile = new File(FILE_DIRECTORY + file.getOriginalFilename());
		myFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(file.getBytes());
		fos.close();
		return new ResponseEntity<Object>("The File Uploaded Successfully",HttpStatus.OK);
	}
}
	
