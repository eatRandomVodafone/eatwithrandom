package com.vodafone.eatwithrandom.controller;

import com.vodafone.eatwithrandom.dto.InfoTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vodafone.eatwithrandom.service.AssignService;

@RestController
@RequestMapping("/eatwithrandom")
@CrossOrigin(origins = "*")
public class AsignacionController {
	
	@Autowired
	private AssignService assignService;
	

	// Este es el rest de assignedTable (9)
    @PostMapping("/asignar")
    public ResponseEntity<?> asignarMesa(){

    	assignService.asignarMesa();
    	return ResponseEntity.ok().build();
    }

	@GetMapping("/infotable")
	public InfoTable getInfoTable() {
		return this.assignService.readInfoTable();
	}
}
