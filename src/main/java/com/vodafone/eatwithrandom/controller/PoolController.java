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
import com.vodafone.eatwithrandom.enums.Status;

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
	
	// - Este es el rest que inserta en la cola de pool (3), Hay que añadirle el action correspodiente (UP)
	// - También controlamos con este rest, las cancelaciones de busqueda y asignación (DOWN), mirando el estado 
	// en el que esta el usuario (5)
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
    		
    		//Dar de alta en la cola pool - UP
    		if(queue.getAction() != null && queue.getAction().equalsIgnoreCase(Actions.UP.toString()) || 
    				user.getStatus().equals(Status.STANDBY.toString())) {
        		if(queue.getQueue().equalsIgnoreCase(Queue.FACETOFACE.toString())) {	
        			//jwt = poolService.insertQeueF2F(user);
        		}
        		else if(queue.getQueue().equalsIgnoreCase(Queue.GROUP.toString())) {
        			jwt = poolService.insertQeueGroup(user, queue.getHorario(), bearerToken);
        		}
        		
        	} 
    		//Eliminar de la cola pool - DOWN
    		else if(queue.getAction() != null && queue.getAction().equalsIgnoreCase(Actions.DOWN.toString()) ||
    				user.getStatus().equals(Status.WAITING.toString())) {
        		if(queue.getQueue().equalsIgnoreCase(Queue.FACETOFACE.toString())) {	
        			//jwt = poolService.deleteQeueF2F(user);
        		}
        		else if(queue.getQueue().equalsIgnoreCase(Queue.GROUP.toString())) {
        			jwt = poolService.deleteQeueGroup(user, bearerToken);
        		}
        		
        	}
    		//Eliminar de la cola asginación - CANCEL
    		else if(queue.getAction() != null && queue.getAction().equalsIgnoreCase(Actions.CANCEL.toString()) ||
    				user.getStatus().equals(Status.ASSIGNED.toString())) {
        		//TODO
        		
        	}
    		    		
    	}
        
    	response.setJwt(jwt);
        
        return response;
    }

}
