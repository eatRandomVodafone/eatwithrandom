package com.vodafone.eatwithrandom.controller;


import com.vodafone.eatwithrandom.service.RecoverPwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eatwithrandom")
public class RecoverPwdController {

    @Autowired
    private RecoverPwdService recoverPwdService;

    @GetMapping("/recoverpwd/{email}")
    public ResponseEntity<?> recoverPassword(@PathVariable String email){
        this.recoverPwdService.generatePassword(email);
        
        return ResponseEntity.ok().build();
    }
}
