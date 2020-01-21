package com.medongo.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.support.DaoSupport;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.medongo.dto.AppointmentDto;
import com.medongo.dto.MasterDto;
import com.medongo.dto.PatientDto;
import com.medongo.dto.ResponseData;
import com.medongo.dto.UserInfoDto;
import com.medongo.dto.VendorDto;
import com.medongo.service.MedongoService;

@RestController
@RequestMapping("/medongo")
@CrossOrigin(origins="*",allowedHeaders="*",allowCredentials="true")
public class MedongoController {
	
    @Autowired
	private MedongoService service;
    
    @InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor = new CustomDateEditor(format, true);
		binder.registerCustomEditor(Date.class, editor);
	}
    
    @PostMapping(path ="/registerUser",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public ResponseData registerUser(@RequestBody UserInfoDto userInfo) {
		ResponseData response = new ResponseData();
        if(service.registerUser(userInfo)) {
        	response.setStatusCode(200);
        	response.setStatus("success");
        	response.setDescription("User Registered Successfully");
        }else {
        	response.setStatusCode(401);
        	response.setStatus("failed");
        	response.setDescription("User Not Registered ");
        }
        return response;
	}//End of Register User
    
    
    @GetMapping(path ="/login",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseData userLogin(String userEmail, String userPassword) {
    	ResponseData response = new ResponseData();
    	UserInfoDto userLoginData=service.userLogin(userEmail,userPassword);
    	
        if(userLoginData!=null) {
        	
        	response.setStatusCode(200);
        	response.setStatus("success");
        	response.setDescription("User Logged In Successfully");
        	System.out.println(Arrays.asList(userLoginData));
        	response.setUserInfoList(Arrays.asList(userLoginData));
        	
        }else {
        	
        	response.setStatusCode(401);
        	response.setStatus("failed");
        	response.setDescription("User Not Logged In ");
        }
        return response;
    }//End of login User
    
    @PostMapping(path = "/registerPatient",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseData registerPatient(@RequestBody PatientDto patientDto) {
    	ResponseData response = new ResponseData();
    	if(service.registerPatient(patientDto)) {
    		response.setStatusCode(200);
        	response.setStatus("success");
        	response.setDescription("Patient  Registered Successfully");
    	}else {
    		response.setStatusCode(401);
        	response.setStatus("failed");
        	response.setDescription("Patient Not Registered ");
    	}
    	return response;
    }
    
    @GetMapping(path="/getAllDoctors",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseData getAllDoctors() {
    	ResponseData response = new ResponseData();
    	List<UserInfoDto> doctorList=service.getDoctors();
    	if(doctorList!=null) {
    		response.setStatusCode(200);
        	response.setStatus("success");
        	response.setDescription("Doctros Record Found");
        	response.setUserInfoList(doctorList);
    	}else {
    		response.setStatusCode(401);
        	response.setStatus("success");
        	response.setDescription("Doctros Record Not Found");
    	}
    	return response;
    }
    
    @GetMapping(path = "/getAllPatient",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseData vewRegisteredPat() {
    	ResponseData response = new ResponseData();
    	List<PatientDto> patientList=service.vewRegisteredPat();
    	if(patientList!=null) {
    		response.setStatusCode(200);
        	response.setStatus("success");
        	response.setDescription("Patient Record Found");
        	response.setPatInfoList(patientList);
    	}else {
    		response.setStatusCode(401);
        	response.setStatus("failed");
        	response.setDescription("Doctros Record Not Found");
    	}
    	return response;
    }
    
    @PostMapping(path = "/addVendor",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseData addVendor(@RequestBody VendorDto vendor) {
    	ResponseData response = new ResponseData();
    	if(service.addVendor(vendor)) {
    		response.setStatusCode(200);
    		response.setStatus("success");
    		response.setDescription("Subcenter Added Successfully..");
    	}else {
    		response.setStatusCode(401);
    		response.setStatus("failed");
    		response.setDescription("Subcenter does not  Added..");
    	}
    	return response;
    }
    @GetMapping(path = "/getAllSubcenters",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseData getSubCenters() {
    	ResponseData response = new ResponseData();
    	List<VendorDto> subCentersList=service.getAllSubCenters();
    	if(subCentersList!=null) {
    		response.setStatusCode(200);
    		response.setStatus("success");
    		response.setDescription("Subcenters data found");
    		response.setSubCentersList(subCentersList);
    	}else {
    		response.setStatusCode(401);
    		response.setStatus("failed");
    		response.setDescription("Subcenters data not found");
    	}
    	return response;
    }
    
    @GetMapping(path = "/getAllAppointments",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseData getAllAppointments(@RequestParam("userId") String userId) {
    	ResponseData response = new ResponseData();
    	List<MasterDto> masterList=service.getAllAppointments(userId);
    	if(masterList!=null) {
    		response.setStatusCode(200);
    		response.setStatus("success");
    		response.setDescription("Appointments List found.");
    		response.setMasterDtoList(masterList);
    	}else {
    		response.setStatusCode(401);
    		response.setStatus("failed");
    		response.setDescription("Appointments list not found");
    	}
    	return response;
    }
    
    @PostMapping(path = "/submitConsult",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseData submitConsult(@RequestBody MasterDto masterDto) {
    	ResponseData response = new ResponseData();
    	if(service.submitConsult(masterDto)) {
    		response.setStatusCode(200);
    		response.setStatus("success");
    		response.setDescription("Consult Completed..");
    		
    	}else {
    		response.setStatusCode(401);
    		response.setStatus("failed");
    		response.setDescription("Consult Not Completed");
    		
    	}
    	return response;
    }
    
    @GetMapping(path = "/getAllUsers",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseData getAllUsers() {
    	ResponseData response = new ResponseData();
    	List<MasterDto> userInfoList=service.getAllUsers();
    	if(userInfoList!=null) {
    		response.setStatusCode(200);
    		response.setStatus("success");
    		response.setDescription("User Data found..");
    		response.setMasterDtoList(userInfoList);
    	}else {
    		response.setStatusCode(401);
    		response.setStatus("failed");
    		response.setDescription("No User Data found..");
    	}
    	return response;
    }
    
    @GetMapping(path = "/generateReport",produces =MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseData generateReport(Date startDate,Date endDate,String vendorId,String doctorId) {
    	ResponseData response = new ResponseData();
    	List<MasterDto> consultedPatList=service.generateReport(startDate,endDate,vendorId,doctorId);
    	if(consultedPatList!=null) {
    		response.setStatusCode(200);
    		response.setStatus("success");
    		response.setDescription("User Data found..");
    		response.setMasterDtoList(consultedPatList);
    	}else {
    		response.setStatusCode(401);
    		response.setStatus("failed");
    		response.setDescription("No User Data found..");
    	}
    	return response;
    }
    
    @GetMapping(path = "/generateMedicinesReport",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseData generateMedicinesReport(String vendorId,String doctorId) {
    	ResponseData response = new ResponseData();
    	List<MasterDto> medicinesMasterList=service.generateMedicinesReport(vendorId, doctorId);
    	if(medicinesMasterList!=null) {
    		response.setStatusCode(200);
    		response.setStatus("success");
    		response.setDescription("Medicines Data found..");
    		response.setMasterDtoList(medicinesMasterList);
    	}else {
    		response.setStatusCode(401);
    		response.setStatus("failed");
    		response.setDescription("No Medicines Data found..");	
    	}
    	return response;
    }
    
}
