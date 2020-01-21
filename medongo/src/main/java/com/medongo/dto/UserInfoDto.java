package com.medongo.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "user_info")
public class UserInfoDto {
	@Id
	@Column(name ="user_id")
	private String userId;
	@Column(name="name",nullable = false)
	private String userName;
	@Column(name="role",nullable =false)
	private String userRole;
	@Column(name="email",nullable =false,unique = true)
	private String userEmail;
	@Column(name ="password")
	private String userPassword;
	@Column(name ="gender")
	private String gender;
	@Column(name ="dob")
	private Date dob;
	@Column(name ="age")
	private Double age;
	@Column(name="adhar_id")
	private long adharId;
	@Column(name="pan_id")
	private String panId;
	@Column(name="voter_id")
	private long voterId;
	@Column(name="phone_no")
	private long phoneNo;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	private VendorDto vendor;*/
	@Column(name = "vendor_id")
	private String vendorId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Double getAge() {
		return age;
	}
	public void setAge(Double age) {
		this.age = age;
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
	/*public VendorDto getVendor() {
		return vendor;
	}
	public void setVendor(VendorDto vendor) {
		this.vendor = vendor;
	}*/
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	
	
}
