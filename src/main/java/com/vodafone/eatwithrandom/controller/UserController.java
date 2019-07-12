package com.vodafone.eatwithrandom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vodafone.eatwithrandom.dto.Login;
import com.vodafone.eatwithrandom.dto.UserResponseDTO;
import com.vodafone.eatwithrandom.model.User;
import com.vodafone.eatwithrandom.service.UserService;

import java.net.URI;


@RestController
@RequestMapping("/eatwithrandom")
@CrossOrigin(origins = "http://18.185.48.95:80")
public class UserController {
	
	@Autowired
    private UserService userService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    
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
    
    @GetMapping("/postsignup/{token}")
    public ResponseEntity<?> postsignup(@PathVariable String token) {
        
        userService.postsignup(token);

        HttpHeaders httpHeaders = new HttpHeaders();

        try{
            URI location = new URI("http://18.185.48.95");
            httpHeaders.setLocation(location);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);

        } catch (Exception e){
            return null;
        }
    }

}
