package com.vodafone.eatwithrandom.controller;

import com.vodafone.eatwithrandom.dto.UserResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eatwithrandom")
public class StatusController {

    @GetMapping("/status")
    public UserResponseDTO getStatus(
    		@RequestHeader("Authorization") String bearerToken){
    	return null;
    }

}
