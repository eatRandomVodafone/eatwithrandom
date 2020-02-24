package com.vodafone.eatwithrandom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vodafone.eatwithrandom.enums.Status;
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
	  
	  @Autowired
	  private UserRepository userRepository;
	
	public String insertQeueF2F(User usuario) {
		 return null;
	}

	public String insertQeueGroup(User usuario, String horario, String bearerToken) {
		  String token = null;
		  if (usuario != null) {
			  Optional<PoolGrupal> poolGrupal = poolGrupalRepository.findUser(usuario.getUserId());
			  if (poolGrupal.isPresent()) {
				  throw new CustomException("User already at pool", HttpStatus.UNPROCESSABLE_ENTITY);
			  }
			  else {
				  PoolGrupal usuarioGrupal = new PoolGrupal();
				  usuarioGrupal.setUserId(usuario.getUserId());
				  usuarioGrupal.setHour(horario);  
				  poolGrupalRepository.saveUserPoolGroup(usuarioGrupal);
				  
				  //Actualizamos STATUS
				  usuario.setStatus(Status.WAITING.toString());
		    	  userRepository.updateUser(usuario);
		    	  
		    	  //Actualizamos JWT
		    	  token = jwtTokenProvider.createToken(usuario);
			  }

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
			  
			  //Actualizamos STATUS
			  usuario.setStatus(Status.STANDBY.toString());
	    	  userRepository.updateUser(usuario);
	    	  
	    	  //Actualizamos JWT
	    	  token = jwtTokenProvider.createToken(usuario);
			  
	        return token;
	    } else {
	        throw new CustomException("Invalid username supplied", HttpStatus.UNPROCESSABLE_ENTITY);
	    }
	}


	public List<PoolGrupal> readUsersByHour(String hour) {

		Optional<List<PoolGrupal>> usersPool = poolGrupalRepository.findByHour(hour);
		if (usersPool.isPresent()) {
			return usersPool.get();
		}

		return null;
	}

	public void deleteAll() {
		poolGrupalRepository.deleteAll();
	}

}
