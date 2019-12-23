package com.medongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.medongo.dto.ResponseData;
import com.medongo.dto.UserInfoDto;
import com.medongo.service.MedongoService;

@RestController
@RequestMapping("/medongo")
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

}
