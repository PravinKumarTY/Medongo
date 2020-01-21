package com.medongo.dto;

public class MedicineDto {
	private String medicineType;
	private String medicineName;
	private Integer dose;
	private Integer days;
	private String freequency;
	private String injections;
	public String getMedicineType() {
		return medicineType;
	}
	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public Integer getDose() {
		return dose;
	}
	public void setDose(Integer dose) {
		this.dose = dose;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public String getFreequency() {
		return freequency;
	}
	public void setFreequency(String freequency) {
		this.freequency = freequency;
	}
	public String getInjections() {
		return injections;
	}
	public void setInjections(String injections) {
		this.injections = injections;
	}
}
