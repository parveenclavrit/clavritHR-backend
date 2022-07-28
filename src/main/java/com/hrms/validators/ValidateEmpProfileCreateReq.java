package com.hrms.validators;

import java.util.ArrayList;
import java.util.List;

import com.hrms.dto.EProfileDataDto;

public class ValidateEmpProfileCreateReq {

	public static List<String> validateReuqest(EProfileDataDto req) {
		List<String> err = new ArrayList<>();
		if(null == req) {
			err.add("Request not valid");
		}
		if(null == req.getEmpHrmsDetails() || null == req.getEmpMasterDetails() || null == req.getEmpPersonalDetails()) {
			err.add("Request not valid");
		}
		
		if(null == req.getEmpMasterDetails().getRole()) {
			err.add("Invalid role details");
		}
		
		return err;
	}
}
