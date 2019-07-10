package com.vodafone.eatwithrandom.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vodafone.eatwithrandom.dto.UserResponseDTO;
import com.vodafone.eatwithrandom.enums.Actions;
import com.vodafone.eatwithrandom.enums.Qeue;
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
    public ResponseEntity<?> signup(
    		@RequestBody User user) {
    	
    	userService.signup(user);
    	
    	return ResponseEntity.ok().build();
    }
    
    @GetMapping("/postsignup/{token}")
    public ResponseEntity<?> postsignup(@PathVariable String token) {
        
        userService.postsignup(token);
        
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/registerQeue")
    public UserResponseDTO postsignup(
   		 @RequestParam String qeue,
   		 @RequestParam String action,
   		 @RequestParam String horario,
   		 @RequestParam String rol,
   		 @RequestParam String area) {  
    	
    	UserResponseDTO response = new UserResponseDTO();
    	String jwt = null;
        
    	if(action.equalsIgnoreCase(Actions.UP.toString())) {
    		if(qeue.equalsIgnoreCase(Qeue.FACETOFACE.toString())) {	
    			jwt = userService.insertQeueF2F();
    		}
    		else if(action.equalsIgnoreCase(Qeue.GROUP.toString())) {
    			jwt = userService.insertQeueGroup(horario);
    		}
    		
    	} else if(action.equalsIgnoreCase(Actions.DOWN.toString())) {
    		if(qeue.equalsIgnoreCase(Qeue.FACETOFACE.toString())) {	
    			jwt = userService.deleteQeueF2F();
    		}
    		else if(action.equalsIgnoreCase(Qeue.GROUP.toString())) {
    			jwt = userService.deleteQeueGroup(horario);
    		}
    	}
    	
    	response.setJwt(jwt);
        
        return response;
    }

}
