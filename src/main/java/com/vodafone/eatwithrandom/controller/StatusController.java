package com.vodafone.eatwithrandom.controller;

import com.vodafone.eatwithrandom.dto.UserResponseDTO;
import com.vodafone.eatwithrandom.service.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eatwithrandom")
@CrossOrigin(origins = "*")
public class StatusController {
	
	@Autowired
	private StatusService statusService;

	// Hay que rehacer este rest, lo único que tiene que hacer es actualizar el JWT del usuario (4)
    @GetMapping("/status")
    public UserResponseDTO getStatus(
    		@RequestHeader("Authorization") String bearerToken){
    	
    	UserResponseDTO response = new UserResponseDTO();	
    	String jwt = statusService.getStatus(bearerToken);
    	response.setJwt(jwt);
    	
    	return response;
    }

}
