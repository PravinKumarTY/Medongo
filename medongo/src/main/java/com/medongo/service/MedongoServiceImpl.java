package com.medongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medongo.dao.MedongoDao;
import com.medongo.dto.PatientDto;
import com.medongo.dto.UserInfoDto;
@Service
public class MedongoServiceImpl implements MedongoService{
	@Autowired
	private MedongoDao dao;

	@Override
	public boolean registerUser(UserInfoDto userInfo) {
		return dao.registerUser(userInfo);
	}

	@Override
	public UserInfoDto userLogin(String userEmail,String userPassword) {
		return dao.userLogin(userEmail,userPassword);
	}

	@Override
	public boolean registerPatient(PatientDto patientDto) {
		return dao.registerPatient(patientDto);
	}

	@Override
	public List<UserInfoDto> getDoctors() {
		return dao.getDoctors();
	}

	@Override
	public List<PatientDto> vewRegisteredPat() {
		return dao.vewRegisteredPat();
	}
}
