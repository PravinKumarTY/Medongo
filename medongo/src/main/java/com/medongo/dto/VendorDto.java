package com.medongo.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="vendor")
public class VendorDto {
	@Id
	@Column(name="vendor_id",nullable =false,unique =true)
	private String vendorId;
	@Column(name ="vendor_name")
	private String vendorName;
	@Column(name="block")
	private String block;
	@Column(name="pin_code",unique = true)
	private String pinCode;
	
	/*@OneToMany(mappedBy = "vendor",cascade = CascadeType.ALL)
	private List<UserInfoDto> userList=new ArrayList<UserInfoDto>();*/
	
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	/*public List<UserInfoDto> getUserList() {
		return userList;
	}
	public void setUserList(List<UserInfoDto> userList) {
		this.userList = userList;
	}*/
	
}
