package com.medongo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medongo.dao.MedongoDao;
import com.medongo.dto.AppointmentDto;
import com.medongo.dto.MasterDto;
import com.medongo.dto.PatientDto;
import com.medongo.dto.UserInfoDto;
import com.medongo.dto.VendorDto;
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

	@Override
	public boolean addVendor(VendorDto vendor) {
		return dao.addVendor(vendor);
	}

	@Override
	public List<VendorDto> getAllSubCenters() {
		return dao.getAllSubCenters();
	}
	@Override
	public List<MasterDto> getAllAppointments(String userId){
		return dao.getAllAppointments(userId);
	}

	@Override
	public boolean submitConsult(MasterDto masterDto) {
		return dao.submitConsult(masterDto);
	}

	@Override
	public List<MasterDto> getAllUsers() {
		return dao.getAllUsers();
	}

	@Override
	public List<MasterDto> generateReport(Date startDate, Date endDate, String vendorId, String doctorId) {
		return dao.generateReport(startDate, endDate, vendorId, doctorId);
	}

	@Override
	public List<MasterDto> generateMedicinesReport(String vendorId, String doctorId) {
		return dao.generateMedicinesReport(vendorId, doctorId);
	}

}
