package com.hrms.service.impl;

import com.hrms.entity.FileUpload;
import com.hrms.entity.MyInfoDetail;
import com.hrms.repository.FileUploadRepository;
import com.hrms.repository.MyInfoRepository;
import com.hrms.service.MyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
@Service
public class MyInfoServiceImpl implements MyInfoService {
    @Autowired
    private MyInfoRepository myinforpo;
    
    @Autowired
    private FileUploadRepository fileUploadRepo;

    @Override
    public MyInfoDetail getMyInfoDetail(int id) {
        Optional<MyInfoDetail> list=myinforpo.findById(id);
        return ! list.isPresent() ? null: list.get();
    }

    @Override
    public List<MyInfoDetail> getAllMyInfo() {
        return myinforpo.findAll();
    }

    @Override
    public void deleteMyInfodetail(int id) {
      myinforpo.deleteById(id);
    }

    @Override
    public MyInfoDetail saveMyInfo(MyInfoDetail myinfodetail) {
        myinforpo.save(myinfodetail);
        return myinfodetail;
    }

	@Override
	public FileUpload saveFile(FileUpload fileUpload) {
		return this.fileUploadRepo.save(fileUpload);
	}

	@Override
	public List<FileUpload> getAllFiles(int empId) {
		// TODO Auto-generated method stub
		return this.fileUploadRepo.getAllFilesByEmpId(empId);
	}
	
	public List<MyInfoDetail> saveAllMyInfoDetails(List<MyInfoDetail> infoList) {

        // Load existing records
        List<MyInfoDetail> existingList = myinforpo.findAll();
        Map<String, MyInfoDetail> existingMap = new HashMap<>();
        for (MyInfoDetail e : existingList) {
            String key = e.getEmp_mail().toLowerCase().trim() + "|" + e.getName().toLowerCase().trim();
            existingMap.put(key, e);
        }

        List<MyInfoDetail> toSave = new ArrayList<>();
        Set<String> seenKeys = new HashSet<>();

        for (MyInfoDetail info : infoList) {
            String key = info.getEmp_mail().toLowerCase().trim() + "|" + info.getName().toLowerCase().trim();

            // Skip duplicates in Excel sheet
            if (seenKeys.contains(key)) continue;
            seenKeys.add(key);

            if (existingMap.containsKey(key)) {
                // Update existing record
                MyInfoDetail existing = existingMap.get(key);
                existing.setAddress(info.getAddress());
                existing.setPhone_num(info.getPhone_num());
                existing.setJoining_date(info.getJoining_date());
                existing.setUploadedFile(info.getUploadedFile() != null ? info.getUploadedFile() : existing.getUploadedFile());
                toSave.add(existing);
            } else {
                // New record
                toSave.add(info);
            }
        }

        // Save all new/updated records
        return myinforpo.saveAll(toSave);
    }
}
