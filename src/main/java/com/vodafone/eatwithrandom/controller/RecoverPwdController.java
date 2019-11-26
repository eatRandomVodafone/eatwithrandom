package com.vodafone.eatwithrandom.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vodafone.eatwithrandom.service.RecoverPwdService;

@RestController
@RequestMapping("/eatwithrandom")
@CrossOrigin(origins = "*")
public class RecoverPwdController {

    @Autowired
    private RecoverPwdService recoverPwdService;

    // Rest para recuperar el password (7)
    @GetMapping("/recoverpwd")
    public ResponseEntity<?> recoverPassword(@RequestParam String email){
        this.recoverPwdService.generatePassword(email);
        
        return ResponseEntity.ok().build();
    }
}
