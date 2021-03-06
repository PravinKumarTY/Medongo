package com.medongo.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name ="appointment")
public class AppointmentDto {
	@Id
	@Column(name = "appt_id",nullable = false,unique = true)
	private String apptId;
	@Column(name ="party_id_from",nullable = false)
	private String partyIdFrom;
	@Column(name = "party_id_to",nullable = false)
	private String partyIdTo;
	@Column(name = "appt_date",nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date apptDate;
	@Column(name = "vendor_id",nullable = false)
	private String vendorId;
	@Column(name = "status_code")
	private int statusCode;
	
	@OneToMany(mappedBy = "appointment",fetch = FetchType.LAZY)
	private List<Prescription> prescreption;
	
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
	public List<Prescription> getPrescreption() {
		return prescreption;
	}
	public void setPrescreption(List<Prescription> prescreption) {
		this.prescreption = prescreption;
	}
	
}
