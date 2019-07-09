package com.vodafone.eatwithrandom.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vodafone.eatwithrandom.dto.UserResponseDTO;
import com.vodafone.eatwithrandom.model.User;
import com.vodafone.eatwithrandom.service.UserService;



@RestController
@RequestMapping("/eatwithrandom")
public class UserController {
	
	@Autowired
    private UserService userService;
	

    @PostMapping("/signin")
    public UserResponseDTO login(
    		 @RequestParam String username,
    		 @RequestParam String password) {
    	
    	UserResponseDTO response = new UserResponseDTO();	
    	String jwt = userService.signin(username, password);
    	
    	response.setJwt(jwt);
    	
    	return response;
      
    }
    
    @PostMapping("/signup")
    public UserResponseDTO signup(
    		@RequestBody User user) {
    	
    	UserResponseDTO response = new UserResponseDTO();	
    	String jwt =  userService.signup(user);
    	
    	response.setJwt(jwt);
    	
    	return response;
    }

}
