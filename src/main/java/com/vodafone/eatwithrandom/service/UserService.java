package com.vodafone.eatwithrandom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vodafone.eatwithrandom.exception.CustomException;
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
	  if (userRepository.checkPassword(username, password).isPresent())
    	  return jwtTokenProvider.createToken(username);
      else
    	  throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
  }

  public String signup(User user) {
    if (!userRepository.findOne(user.getName()).isPresent()) {
      //user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.saveUser(user);
      return jwtTokenProvider.createToken(user.getName());
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
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
