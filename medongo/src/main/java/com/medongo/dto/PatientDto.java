package com.medongo.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="patient_info")
public class PatientDto {
	@Id
	@Column(name="pat_id")
	private String patId;
	@Column(name="pat_name")
	private String patName;
	@Column(name="address")
	private String address;
	@Column(name="email")
	private String email;
	@Column(name ="phone_no")
	private long phoneNo;
	@Column(name="gender")
	private String gender;
	@Column(name="age")
	private Double age;
	@Column(name="dob")
	private Date dob;
	@Column(name="height")
	private Double height;
	@Column(name="weight")
	private Double weight;
	@Column(name ="blood_pressure")
	private Double bloodPressure;
	@Column(name = "sugar")
	private Double sugar;
	@Column(name = "respiratory_rate")
	private Double respiratoryRate;
	@Column(name="fever")
	private String fever;
	@Column(name="fever_type")
	private String feverType;
	@Column(name="symptoms")
	private String symptoms;
	@Column(name = "party_id_to",nullable = false)
	private String doctorId;
	
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
	
}
