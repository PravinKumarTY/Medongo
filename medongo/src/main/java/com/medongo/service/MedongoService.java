package com.medongo.service;

import com.medongo.dto.PatientDto;
import com.medongo.dto.UserInfoDto;

public interface MedongoService {
	public boolean registerUser(UserInfoDto userInfo);
	public UserInfoDto userLogin(String userEmail,String userPassword);
	public boolean registerPatient(PatientDto patientDto);
}
