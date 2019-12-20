package com.medongo.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
	
	
}
