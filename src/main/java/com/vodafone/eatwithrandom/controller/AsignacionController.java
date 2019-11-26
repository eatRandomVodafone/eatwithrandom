package com.vodafone.eatwithrandom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vodafone.eatwithrandom.service.AssignService;

@RestController
@RequestMapping("/eatwithrandom")
@CrossOrigin(origins = "*")
public class AsignacionController {
	
	@Autowired
	private AssignService assignService;
	

	// Este es el rest de assignedTable (9)
    @GetMapping("/asignar")
    public ResponseEntity<?> asignarMesa(){
    	
    	
    	assignService.asignarMesa();
    	
    	return ResponseEntity.ok().build();
    }
    
    //TODO: Crear el rest de getInfoTable (2)

}
