package com.vodafone.eatwithrandom.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		String status = null;
		ArrayList<String> detalleAsignacion = new ArrayList<String>();
		
		String username = userModel.getCurrentUser().getUsername();
    	Optional<User> optionalUser = userRepository.findOne(username);
    	
    	if (optionalUser.isPresent()) {
    		//Checkear si está en pool grupo
    		Optional<PoolGrupal> userPool = poolGrupalRepository.findUser(optionalUser.get().getUserId());
    		if (userPool.isPresent()) {
    			status = "esperando_grupo_" + userPool.get().getHoraComida();    		
    		}
    		else {
	        	//Checkear si está en reserva grupo
	    		Optional<ReservaGrupal> reservaGrupal = reservaGrupalRepository.findByUser(optionalUser.get().getUserId());
	    		if (reservaGrupal.isPresent()) {
	    			status = "mesaAsignada_grupo";
	    			reservaGrupal.get().getUserId().forEach((u) -> detalleAsignacion.add(u));
	    		}

    		}
    		
			
    	}
		
    	//Actualizar JWT (con estado y detalle)
		HashMap<String, Object> claimsMap = new HashMap<String, Object>();
		claimsMap.put("status", status);
		if (!detalleAsignacion.isEmpty())
			claimsMap.put("detalleAsignacion", detalleAsignacion);
		String token = jwtTokenProvider.updateToken(bearerToken, claimsMap);
		  
		return token;

	}

}
