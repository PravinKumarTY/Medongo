package com.medongo.dao;

import java.util.Date;
import java.util.List;

import com.medongo.dto.MasterDto;
import com.medongo.dto.PatientDto;
import com.medongo.dto.UserInfoDto;
import com.medongo.dto.VendorDto;


public interface MedongoDao {
	public boolean registerUser(UserInfoDto userInfo);
	public UserInfoDto userLogin(String userEmail,String userPassword);
	public boolean registerPatient(PatientDto patientDto);
	public List<UserInfoDto> getDoctors();
	public List<PatientDto> vewRegisteredPat();
	public boolean addVendor(VendorDto vendor);
	public List<VendorDto> getAllSubCenters();
	public List<MasterDto> getAllAppointments(String userId);
	public boolean submitConsult(MasterDto masterDto);
	public List<MasterDto> getAllUsers();
	public List<MasterDto> generateReport(Date startDate,Date endDate,String vendorId,String doctorId);
	public List<MasterDto> generateMedicinesReport(String vendorId,String doctorId);
}
