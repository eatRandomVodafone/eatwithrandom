package com.vodafone.eatwithrandom.controller;


import com.vodafone.eatwithrandom.service.RecoverPwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eatwithrandom")
public class RecoverPwdController {

    @Autowired
    private RecoverPwdService recoverPwdService;

    @GetMapping("/recoverpwd")
    public ResponseEntity<?> recoverPassword(){
        this.recoverPwdService.generatePassword();
        
        //Send mail - invocar servicio de envío de mail.
        
        return ResponseEntity.ok().build();
    }
}
