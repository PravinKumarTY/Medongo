package com.medongo.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.medongo.dto.PatientDto;
import com.medongo.dto.ResponseData;
import com.medongo.dto.UserInfoDto;
import com.medongo.service.MedongoService;

@RestController
@RequestMapping("/medongo")
@CrossOrigin
public class MedongoController {
	
    @Autowired
	private MedongoService service;

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
    }//End of registerUser
    
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
    
    

}
