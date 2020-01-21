package com.medongo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MasterDto implements Serializable{
	private String apptId;
	private String partyIdFrom;
	private String partyIdTo;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date apptDate;
	private String vendorId;
	private String vendorName;
	private String userId;
	private String userName;
	private String userRole;
	private long adharId;
	private String panId;
	private long voterId;
	private int statusCode;
	private String patId;
	private String patName;
	private String address;
	private String email;
	private long phoneNo;
	private String gender;
	private Double age;
	private Date dob;
	private Double height;
	private Double weight;
	private Double bloodPressure;
	private Double sugar;
	private Double respiratoryRate;
	private String fever;
	private String feverType;
	private String symptoms;
	private String doctorId;
	private List<MedicineDto> prescriptionDet;
	private List<Prescription> medicinesList;
	private String advice;
	private Date startDate;
	private Date endDate;
	
	public String getApptId() {
		return apptId;
	}
	public void setApptId(String apptId) {
		this.apptId = apptId;
	}
	public String getPartyIdFrom() {
		return partyIdFrom;
	}
	public void setPartyIdFrom(String partyIdFrom) {
		this.partyIdFrom = partyIdFrom;
	}
	public String getPartyIdTo() {
		return partyIdTo;
	}
	public void setPartyIdTo(String partyIdTo) {
		this.partyIdTo = partyIdTo;
	}
	public Date getApptDate() {
		return apptDate;
	}
	public void setApptDate(Date apptDate) {
		this.apptDate = apptDate;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getPatId() {
		return patId;
	}
	public void setPatId(String patId) {
		this.patId = patId;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Double getAge() {
		return age;
	}
	public void setAge(Double age) {
		this.age = age;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(Double bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public Double getSugar() {
		return sugar;
	}
	public void setSugar(Double sugar) {
		this.sugar = sugar;
	}
	public Double getRespiratoryRate() {
		return respiratoryRate;
	}
	public void setRespiratoryRate(Double respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}
	public String getFever() {
		return fever;
	}
	public void setFever(String fever) {
		this.fever = fever;
	}
	public String getFeverType() {
		return feverType;
	}
	public void setFeverType(String feverType) {
		this.feverType = feverType;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public List<MedicineDto> getPrescriptionDet() {
		return prescriptionDet;
	}
	public void setPrescriptionDet(List<MedicineDto> prescriptionDet) {
		this.prescriptionDet = prescriptionDet;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public long getAdharId() {
		return adharId;
	}
	public void setAdharId(long adharId) {
		this.adharId = adharId;
	}
	public String getPanId() {
		return panId;
	}
	public void setPanId(String panId) {
		this.panId = panId;
	}
	public long getVoterId() {
		return voterId;
	}
	public void setVoterId(long voterId) {
		this.voterId = voterId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<Prescription> getMedicinesList() {
		return medicinesList;
	}
	public void setMedicinesList(List<Prescription> medicinesList) {
		this.medicinesList = medicinesList;
	}
	
}
