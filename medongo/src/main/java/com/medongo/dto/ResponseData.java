package com.medongo.dto;

import java.util.List;

public class ResponseData {
	private int statusCode;
	private String status;
	private String description;
	private List<UserInfoDto> userInfoList;
	private List<PatientDto> patInfoList;
	private List<VendorDto> subCentersList;
	private List<AppointmentDto> appointmentList;
	private List<MasterDto> masterDtoList;
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<UserInfoDto> getUserInfoList() {
		return userInfoList;
	}
	public void setUserInfoList(List<UserInfoDto> userInfoList) {
		this.userInfoList = userInfoList;
	}
	public List<PatientDto> getPatInfoList() {
		return patInfoList;
	}
	public void setPatInfoList(List<PatientDto> patInfoList) {
		this.patInfoList = patInfoList;
	}
	public List<VendorDto> getSubCentersList() {
		return subCentersList;
	}
	public void setSubCentersList(List<VendorDto> subCentersList) {
		this.subCentersList = subCentersList;
	}
	public List<AppointmentDto> getAppointmentList() {
		return appointmentList;
	}
	public void setAppointmentList(List<AppointmentDto> appointmentList) {
		this.appointmentList = appointmentList;
	}
	public List<MasterDto> getMasterDtoList() {
		return masterDtoList;
	}
	public void setMasterDtoList(List<MasterDto> masterDtoList) {
		this.masterDtoList = masterDtoList;
	}
	
}
