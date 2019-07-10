package com.vodafone.eatwithrandom.controller;

import com.vodafone.eatwithrandom.dto.UserResponseDTO;
import com.vodafone.eatwithrandom.service.AssignService;
import com.vodafone.eatwithrandom.service.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eatwithrandom")
public class AsignacionController {
	
	@Autowired
	private AssignService assignService;
	

    @GetMapping("/asignar")
    public ResponseEntity<?> asignarMesa(){
    	
    	
    	assignService.asignarMesa();
    	
    	return ResponseEntity.ok().build();
    }

}
