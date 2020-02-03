package com.vodafone.eatwithrandom.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vodafone.eatwithrandom.exception.CustomException;
import com.vodafone.eatwithrandom.model.PoolGrupal;
import com.vodafone.eatwithrandom.model.ReservaGrupal;
import com.vodafone.eatwithrandom.model.User;
import com.vodafone.eatwithrandom.repository.PoolGrupalRepository;
import com.vodafone.eatwithrandom.repository.ReservaGrupalRepository;
import com.vodafone.eatwithrandom.repository.UserRepository;
import com.vodafone.eatwithrandom.security.JwtTokenProvider;

@Service
public class StatusService {
	
	@Autowired
    private UserContextService userModel;
	
	@Autowired
	private PoolGrupalRepository poolGrupalRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReservaGrupalRepository reservaGrupalRepository;
	
	@Autowired
    private JwtTokenProvider jwtTokenProvider;
	
	public String getStatus(String bearerToken) {
				
		String username = userModel.getCurrentUser().getUsername();
    	Optional<User> optionalUser = userRepository.findOne(username);
    	
    	if (optionalUser.isPresent()) {
    		//Se refresca la fecha de expiración
    		return jwtTokenProvider.createToken(optionalUser.get());
    	}
    	else
    		throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
				  
	}

}
