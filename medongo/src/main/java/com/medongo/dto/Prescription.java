package com.medongo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="medicines")
public class Prescription {
	@Id
	@GeneratedValue
	@Column(name = "pres_id")
	private Integer presId;
	@Column(name = "tablets")
	private String tablets;
	@Column(name = "medicine_type")
	private String medicineType;
	@Column(name = "syrups")
	private String syrups;
	@Column(name = "injections")
	private String injections;
	@Column(name = "drops")
	private String drops;
	@Column(name = "total_tablets")
	private Integer totalTablets;
	@Column(name = "total_injection")
	private Integer totalInjection;
	@Column
	private String freequency;
	
	public Integer getTotalInjection() {
		return totalInjection;
	}
	public void setTotalInjection(Integer totalInjection) {
		this.totalInjection = totalInjection;
	}
	@ManyToOne
	@JoinColumn(name="appt_id")
	private AppointmentDto appointment;
	public Integer getPresId() {
		return presId;
	}
	public void setPresId(Integer presId) {
		this.presId = presId;
	}
	public String getTablets() {
		return tablets;
	}
	public void setTablets(String tablets) {
		this.tablets = tablets;
	}
	public String getSyrups() {
		return syrups;
	}
	public void setSyrups(String syrups) {
		this.syrups = syrups;
	}
	public String getInjections() {
		return injections;
	}
	public void setInjections(String injections) {
		this.injections = injections;
	}
	public String getDrops() {
		return drops;
	}
	public void setDrops(String drops) {
		this.drops = drops;
	}
	public Integer getTotalTablets() {
		return totalTablets;
	}
	public void setTotalTablets(Integer totalTablets) {
		this.totalTablets = totalTablets;
	}
	public AppointmentDto getAppointment() {
		return appointment;
	}
	public void setAppointment(AppointmentDto appointment) {
		this.appointment = appointment;
	}
	public String getMedicineType() {
		return medicineType;
	}
	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}
	public String getFreequency() {
		return freequency;
	}
	public void setFreequency(String freequency) {
		this.freequency = freequency;
	}

}
