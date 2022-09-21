package com.hrms.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.websocket.server.PathParam;

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
import com.hrms.entity.FileUpload;
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
		   
		   return this.myinfoser.getMyInfoDetail(Integer.parseInt(Id));
		   }
	
	@GetMapping("/files/{Id}")
	   public List<FileUpload> getFiles(@PathVariable String Id) {
		   return this.myinfoser.getAllFiles(Integer.parseInt(Id));
		   }
	

	@PostMapping(value = "/uploadFile/{id}", consumes = "multipart/form-data")
	public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file, @PathVariable String id) throws IOException {
		File myFile = new File(FILE_DIRECTORY + file.getOriginalFilename());
		
		myFile.createNewFile();
		String fileName = file.getOriginalFilename();
       
		System.out.println("file Name : "+ fileName + " Id : "+id);
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(file.getBytes());
		fos.close();
		FileUpload fileUpload = new FileUpload();
		fileUpload.setEmpId(Integer.parseInt(id));
		fileUpload.setFileName(fileName);
		this.myinfoser.saveFile(fileUpload);
		return new ResponseEntity<Object>("The File Uploaded Successfully",HttpStatus.OK);
	}
}
	
