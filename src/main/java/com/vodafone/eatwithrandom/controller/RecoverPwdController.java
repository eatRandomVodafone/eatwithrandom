package com.vodafone.eatwithrandom.controller;


import com.vodafone.eatwithrandom.service.RecoverPwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recoverpwd")
public class RecoverPwdController {

    @Autowired
    private RecoverPwdService recoverPwdService;

    @GetMapping("/recoverpwd/{username}")
    public String recoverPassword(@PathVariable String username){
        return this.recoverPwdService.generatePassword(username);
    }
}