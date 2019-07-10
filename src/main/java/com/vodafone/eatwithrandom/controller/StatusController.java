package com.vodafone.eatwithrandom.controller;

import com.vodafone.eatwithrandom.dto.UserResponseDTO;
import com.vodafone.eatwithrandom.service.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eatwithrandom")
public class StatusController {
	
	@Autowired
	private StatusService statusService;

    @GetMapping("/status")
    public UserResponseDTO getStatus(
    		@RequestHeader("Authorization") String bearerToken){
    	
    	UserResponseDTO response = new UserResponseDTO();	
    	String jwt = statusService.getStatus(bearerToken);
    	response.setJwt(jwt);
    	
    	return response;
    }

}
