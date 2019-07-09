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
    if (!userRepository.findOne(user.getUsername()).isPresent()) {
      //user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.saveUser(user);
      return jwtTokenProvider.createToken(user.getUsername());
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  /*public void delete(String username) {
    userRepository.deleteUser(username);
  }*/

  /*public User search(String username) {
    User user = userRepository.findOne(username).get();
    if (user == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return user;
  }*/

  /*public User whoami(HttpServletRequest req) {
    return userRepository.findOne(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req))).get();
  }*/

  /*public String refresh(String username) {
    return jwtTokenProvider.createToken(username);
  }*/

}
