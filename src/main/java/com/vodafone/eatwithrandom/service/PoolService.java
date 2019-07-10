package com.vodafone.eatwithrandom.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vodafone.eatwithrandom.exception.CustomException;
import com.vodafone.eatwithrandom.model.PoolGrupal;
import com.vodafone.eatwithrandom.model.User;
import com.vodafone.eatwithrandom.repository.PoolGrupalRepository;
import com.vodafone.eatwithrandom.repository.UserRepository;
import com.vodafone.eatwithrandom.security.JwtTokenProvider;

@Service
public class PoolService {
	
	  @Autowired
	  private PoolGrupalRepository poolGrupalRepository;

	  @Autowired
	  private PasswordEncoder passwordEncoder;

	  @Autowired
	  private JwtTokenProvider jwtTokenProvider;
	
	public String insertQeueF2F(User usuario) {
		 return null;
	}

	public String insertQeueGroup(User usuario, String horario, String bearerToken) {
		  String token = null;
		  if (usuario != null) {
			  PoolGrupal usuarioGrupal = new PoolGrupal();
			  usuarioGrupal.setUserId(usuario.getUserId());
			  usuarioGrupal.setHoraComida(horario);  
			  poolGrupalRepository.saveUserPoolGroup(usuarioGrupal);
			  
			  //Actualizar JWT
			  HashMap<String, Object> claimsMap = new HashMap<String, Object>();
			  claimsMap.put("status", "esperando_grupo_" + horario);
			  token = jwtTokenProvider.updateToken(bearerToken, claimsMap);
			  
	        return token;
	    } else {
	        throw new CustomException("Invalid username supplied", HttpStatus.UNPROCESSABLE_ENTITY);
	    }
	}

	public String deleteQeueF2F(User usuario) {
		 return null;
	}

	public String deleteQeueGroup(User usuario, String bearerToken) {
		  String token = null;
		  if (usuario != null) {
			  poolGrupalRepository.deleteUserPoolGroup(usuario.getUserId());
			  
			  //Actualizar JWT
			  HashMap<String, Object> claimsMap = new HashMap<String, Object>();
			  claimsMap.put("status", null);
			  token = jwtTokenProvider.updateToken(bearerToken, claimsMap);
			  
	        return token;
	    } else {
	        throw new CustomException("Invalid username supplied", HttpStatus.UNPROCESSABLE_ENTITY);
	    }
	}

}
