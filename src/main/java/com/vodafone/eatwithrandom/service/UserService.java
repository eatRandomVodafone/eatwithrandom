package com.vodafone.eatwithrandom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vodafone.eatwithrandom.exception.CustomException;

import com.vodafone.eatwithrandom.model.PoolGrupal;

import com.vodafone.eatwithrandom.model.TempUser;

import com.vodafone.eatwithrandom.model.User;
import com.vodafone.eatwithrandom.repository.UserRepository;
import com.vodafone.eatwithrandom.security.JwtTokenProvider;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @Autowired
  private EmailService emailService;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;


  public String signin(String username, String password) {
	  Optional<User> user = userRepository.findOne(username);
	  if (user.isPresent()) {
		  if (passwordEncoder.matches(password, user.get().getPassword()))
			  return jwtTokenProvider.createToken(user.get());
		  else
			  throw new CustomException("Invalid password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
	  } 
      else
    	  throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
  }

  public void signup(User user) {
    if (!userRepository.findOne(user.getUsername()).isPresent()) {
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	//Check patron usuario
    	String jwt = jwtTokenProvider.createToken(user);
    	String token = userRepository.saveTempUser(jwt);
    	//Send mail
    	emailService.sendEmail("Confirmación de registro", "Estás a un paso de crear tu usuario. Confirma haciendo click aquí:"
        		+ ":http://localhost:4444/eatwithrandom/postsignup/"+ token, user.getUsername());
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }
  
  public String postsignup(String token) {
      if (token != null) {
    	  //Recuperamos el usuario temporal
    	  Optional<TempUser> tempUser = userRepository.getTempUser(token);
    	  if (tempUser.isPresent()) {
    		  //Obtenemos el usuario del jwt
              User user = jwtTokenProvider.getUser(tempUser.get().getJwt());
              //Creamos el usuario en la BD
              userRepository.saveUser(user);
              //Borramos el usuario temporal
              userRepository.deleteTempUser(tempUser.get());
              return tempUser.get().getJwt(); 
    	  }
    	  else {
    		  throw new CustomException("Token dont exist", HttpStatus.UNPROCESSABLE_ENTITY);
    	  }
    	  
      } else {
          throw new CustomException("Invalid username supplied", HttpStatus.UNPROCESSABLE_ENTITY);
      }
  }

	
  /*public void delete(String name) {
    userRepository.deleteUser(name);
  }*/

  /*public User search(String name) {
    User user = userRepository.findOne(name).get();
    if (user == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return user;
  }*/

  /*public User whoami(HttpServletRequest req) {
    return userRepository.findOne(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req))).get();
  }*/

  /*public String refresh(String name) {
    return jwtTokenProvider.createToken(name);
  }*/

}
