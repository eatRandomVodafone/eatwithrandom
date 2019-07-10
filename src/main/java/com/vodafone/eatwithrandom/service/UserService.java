package com.vodafone.eatwithrandom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vodafone.eatwithrandom.exception.CustomException;
import com.vodafone.eatwithrandom.model.PoolGrupal;
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
  private JwtTokenProvider jwtTokenProvider;


  public String signin(String username, String password) {
	  Optional<User> user = userRepository.checkPassword(username, password);
	  if (user.isPresent()) {
		  return jwtTokenProvider.createToken(user.get());
	  } 
      else
    	  throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
  }

  public void signup(User user) {
    if (!userRepository.findOne(user.getUsername()).isPresent()) {
      //user.setPassword(passwordEncoder.encode(user.getPassword()));
    	//Check patron usuario
    	String jwt = jwtTokenProvider.createToken(user);
    	//Send mail
    	userRepository.saveUser(user);
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }
  
  public String insertQeueF2F() {
	  String token = null;
      if (username != null) {
          User user = jwtTokenProvider.getUser(token);
          userRepository.saveUser(user);
          return token;
      } else {
          throw new CustomException("Invalid username supplied", HttpStatus.UNPROCESSABLE_ENTITY);
      }
  }
  
  public String insertQeueGroup(String horario) {
	  String token = null;
	  PoolGrupal usuarioGrupal = new PoolGrupal();
	  usuarioGrupal.setUserId(userId);
      if (username != null) {
          User user = jwtTokenProvider.getUser(token);
          userRepository.saveUser(user);
          return token;
      } else {
          throw new CustomException("Invalid username supplied", HttpStatus.UNPROCESSABLE_ENTITY);
      }
  }
  
  public String deleteQeueF2F() {
	  String token = null;
      if (username != null) {
          User user = jwtTokenProvider.getUser(token);
          userRepository.saveUser(user);
          return token;
      } else {
          throw new CustomException("Invalid username supplied", HttpStatus.UNPROCESSABLE_ENTITY);
      }
  }
  
  public String deleteQeueGroup(String horario) {
	  String token = null;
      if (username != null) {
          User user = jwtTokenProvider.getUser(token);
          userRepository.saveUser(user);
          return token;
      } else {
          throw new CustomException("Invalid username supplied", HttpStatus.UNPROCESSABLE_ENTITY);
      }
  }
  
  public String postsignup(String username) {
	  String token = null;
      if (username != null) {
          User user = jwtTokenProvider.getUser(token);
          userRepository.saveUser(user);
          return token;
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
