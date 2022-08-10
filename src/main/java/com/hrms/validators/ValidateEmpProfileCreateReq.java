package com.hrms.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.hrms.dto.EProfileDataDto;

public class ValidateEmpProfileCreateReq {

	public static List<String> validateRequest(EProfileDataDto req) {
		List<String> err = new ArrayList<>();
		if (null == req) {
			err.add("Request not valid");
		}
		if (null == req.getEmpHrmsDetails() || null == req.getEmpMasterDetails()
				|| null == req.getEmpPersonalDetails()) {
			err.add("Request not valid");
		}

		if (!StringUtils.hasLength(req.getEmpMasterDetails().getRole())) {
			err.add("Invalid role details");
		}

		if (null == req.getEmpMasterDetails().getActive()) {
			err.add("Invalid Active details");

		}

		if (!StringUtils.hasLength(req.getEmpMasterDetails().getType())) {
			err.add("Invalid Type details");
		}

		if (!StringUtils.hasLength(req.getEmpHrmsDetails().getEmail())) {
			err.add("Invalid Email details");
		}

		if (!StringUtils.hasLength(req.getEmpHrmsDetails().getDepartment())) {
			err.add("Invalid department details");
		}

		if (null == req.getEmpHrmsDetails().getDoj()) {
			err.add("Invalid doj details");
		}

		if (!StringUtils.hasLength(req.getEmpPersonalDetails().getName())) {
			err.add("Invalid Name details");
		}

		if (!StringUtils.hasLength(req.getEmpPersonalDetails().getGender())) {
			err.add("Invalid gender details");
		}

		if (req.getEmpPersonalDetails().getAge()<= 18) {
			err.add("Invalid age details");
		}

		if (!StringUtils.hasLength(req.getEmpPersonalDetails().getAddress())) {
			err.add("Invalid address details");
		}

		if (!StringUtils.hasLength(req.getEmpPersonalDetails().getOther_field_1())) {
			err.add("Invalid otherfield1 details");
		}

		if (!StringUtils.hasLength(req.getEmpPersonalDetails().getOther_field_2())) {
			err.add("Invalid otherfield2 details");
		}

		return err;
	}

}
