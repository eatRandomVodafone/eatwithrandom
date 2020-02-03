package com.vodafone.eatwithrandom.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vodafone.eatwithrandom.dto.Login;
import com.vodafone.eatwithrandom.dto.UserResponseDTO;
import com.vodafone.eatwithrandom.enums.Actions;
import com.vodafone.eatwithrandom.exception.CustomException;
import com.vodafone.eatwithrandom.model.User;
import com.vodafone.eatwithrandom.service.UserContextService;
import com.vodafone.eatwithrandom.service.UserService;


@RestController
@RequestMapping("/eatwithrandom")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private UserContextService userModel;
    
    @PostMapping("/signin")
    public UserResponseDTO login(
    		@RequestBody Login login) {
    	
    	UserResponseDTO response = new UserResponseDTO();	
    	String jwt = userService.signin(login.getUsername(), login.getPassword());
    	
    	response.setJwt(jwt);
    	
    	return response;
      
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> signup(
    		@RequestBody User user) {
    	
    	userService.signup(user);

    	return ResponseEntity.ok().build();
    }
    
    // Falta hacer lo de las exception en este caso
    @GetMapping("/postsignup/{token}")
    public ResponseEntity<?> postsignup(@PathVariable String token) {
        
        String locationURL = userService.postsignup(token);

        HttpHeaders httpHeaders = new HttpHeaders();

        try{
            URI location = new URI(locationURL);
            httpHeaders.setLocation(location);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);

        } catch (Exception e){
            return null;
        }
    }
    
    @PostMapping("/updateuser/{operation}")
    public UserResponseDTO updateuser(@PathVariable String operation,
    		@RequestBody User user) {
    	
    	UserResponseDTO response = new UserResponseDTO();
    	String jwt;
    	
    	String username = userModel.getCurrentUser().getUsername();
    	
    	if(Actions.UPDATEPROFILE.toString().equalsIgnoreCase(operation)) {
    		jwt = userService.updateProfile(username, user);
    	} else if (Actions.UPDATEPASSWORD.toString().equalsIgnoreCase(operation)) {
    		jwt = userService.updatePassword(username, user);
    	} else {
    		throw new CustomException("operation doesnt exist", HttpStatus.UNPROCESSABLE_ENTITY);
    	}
    	
    	response.setJwt(jwt);
    	
    	return response;
    }
}
