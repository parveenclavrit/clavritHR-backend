package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.FileUpload;

public interface FileUploadRepository extends JpaRepository<FileUpload, Integer> {

	List<FileUpload> getAllFilesByEmpId(int empId);
}
