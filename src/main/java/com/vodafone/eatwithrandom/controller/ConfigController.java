package com.vodafone.eatwithrandom.controller;

import com.vodafone.eatwithrandom.model.Config;
import com.vodafone.eatwithrandom.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eatwithrandom")
@CrossOrigin(origins = "*")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @GetMapping("/config")
    public Config readconfig(){
        return this.configService.readConfig();
    }

}
