package com.vodafone.eatwithrandom.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vodafone.eatwithrandom.dto.RegisterQueueDTO;
import com.vodafone.eatwithrandom.dto.UserResponseDTO;
import com.vodafone.eatwithrandom.enums.Actions;
import com.vodafone.eatwithrandom.enums.Queue;
import com.vodafone.eatwithrandom.model.User;
import com.vodafone.eatwithrandom.repository.UserRepository;
import com.vodafone.eatwithrandom.service.PoolService;
import com.vodafone.eatwithrandom.service.UserContextService;

@RestController
@RequestMapping("/eatwithrandom")
@CrossOrigin(origins = "*")
public class PoolController {
	
	@Autowired
    private UserContextService userModel;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PoolService poolService;
	
	@PostMapping("/registerQueue")
    public UserResponseDTO postsignup(
    		@RequestBody RegisterQueueDTO queue,
   		 	@RequestHeader("Authorization") String bearerToken) {  
    	
    	UserResponseDTO response = new UserResponseDTO();
    	String jwt = null;    	
    	
    	String username = userModel.getCurrentUser().getUsername();
    	Optional<User> optionalUser = userRepository.findOne(username);
    	
    	if (optionalUser.isPresent()) {
    		User user = optionalUser.get();
    		
    		if(queue.getAction().equalsIgnoreCase(Actions.UP.toString())) {
        		if(queue.getQueue().equalsIgnoreCase(Queue.FACETOFACE.toString())) {	
        			jwt = poolService.insertQeueF2F(user);
        		}
        		else if(queue.getQueue().equalsIgnoreCase(Queue.GROUP.toString())) {
        			jwt = poolService.insertQeueGroup(user, queue.getHorario(), bearerToken);
        		}
        		
        	} else if(queue.getAction().equalsIgnoreCase(Actions.DOWN.toString())) {
        		if(queue.getQueue().equalsIgnoreCase(Queue.FACETOFACE.toString())) {	
        			jwt = poolService.deleteQeueF2F(user);
        		}
        		else if(queue.getQueue().equalsIgnoreCase(Queue.GROUP.toString())) {
        			jwt = poolService.deleteQeueGroup(user, bearerToken);
        		}
        	}
    	}
        
    	response.setJwt(jwt);
        
        return response;
    }

}
