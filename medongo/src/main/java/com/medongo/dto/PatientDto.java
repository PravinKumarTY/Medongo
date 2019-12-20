package com.medongo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="patient_info")
public class PatientDto {
	@Id
	@Column(name="pat_id")
	private String patId;
	@Column(name="height")
	private Double height;
	@Column(name="weight")
	private Double weight;
	@Column(name = "blood_pressure")
	private Double bp;
	@Column(name="sugar")
	private Double sugar;
	@Column(name="respiratory_rate")
	private Double respiratoryRate;
	public String getPatId() {
		return patId;
	}
	public void setPatId(String patId) {
		this.patId = patId;
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
	public Double getBp() {
		return bp;
	}
	public void setBp(Double bp) {
		this.bp = bp;
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
}
