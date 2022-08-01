package com.hrms.service.impl;

import com.hrms.entity.MyInfoDetail;
import com.hrms.repository.MyInfoRepository;
import com.hrms.service.MyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MyInfoServiceImpl implements MyInfoService {
    @Autowired
    private MyInfoRepository myinforpo;

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
}
