package com.medongo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="diseases_info")
public class DiseasesDto {
	@Id
	@Column(name = "appt_id")
	private String apptId;
	@Column(name="diseases_name")
	private String disesesName;
	
	public String getApptId() {
		return apptId;
	}
	public void setApptId(String apptId) {
		this.apptId = apptId;
	}
	public String getDisesesName() {
		return disesesName;
	}
	public void setDisesesName(String disesesName) {
		this.disesesName = disesesName;
	}
	
}
