package com.vodafone.eatwithrandom.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vodafone.eatwithrandom.enums.subjectsEmail;
import com.vodafone.eatwithrandom.exception.CustomException;
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
	
	@Value("${mail.endPointTemplate:http://localhost:4444}")
	private String endPoint;

	public String signin(String username, String password) {
		Optional<User> user = userRepository.findOne(username);
		if (user.isPresent()) {
			if (passwordEncoder.matches(password, user.get().getPassword()))
				return jwtTokenProvider.createToken(user.get());
			else
				throw new CustomException("Invalid password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		} else
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
	}

	public void signup(User user) {
		if (!userRepository.findOne(user.getUsername()).isPresent()) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			String jwt = jwtTokenProvider.createToken(user);
			String token = userRepository.saveTempUser(jwt);
			
			ArrayList<String> values = new ArrayList<String>();
			
			values.add(endPoint);
			values.add(token);

			emailService.sendEmail(subjectsEmail.SIGNUP.toString(), user.getUsername(), "signup.html", values);
		} else {
			throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public String postsignup(String token) {
		if (token != null) {
			// Recuperamos el usuario temporal
			Optional<TempUser> tempUser = userRepository.getTempUser(token);
			if (tempUser.isPresent()) {
				// Obtenemos el usuario del jwt
				User user = jwtTokenProvider.getUser(tempUser.get().getJwt());
				// Creamos el usuario en la BD
				userRepository.saveUser(user);
				// Borramos el usuario temporal
				userRepository.deleteTempUser(tempUser.get());
				//TODO: el registro ha ido ok, tenemos que redirigir a una URL para informar al cliente
				return "http://18.185.48.95";
			} else {
				//TODO: el registro ha mal, tenemos que redirigir a una URL para informar al cliente
				return "http://18.185.48.95";
			}

		} else {
			//TODO: el registro ha mal, tenemos que redirigir a una URL para informar al cliente
			return "http://18.185.48.95";
		}
	}
	
	public String updateProfile(String username, User user) {		
		Optional<User> userDB = userRepository.findOne(username);		
		if (userDB.isPresent()) {
			User newUser = userDB.get();
			newUser.setName(user.getName() != "" ? user.getName() : newUser.getName());
			newUser.setDepartment(user.getDepartment() != "" ? user.getDepartment() : newUser.getDepartment());
			newUser.setRole(user.getRole() != "" ? user.getRole() : newUser.getRole());
			newUser.setComment(user.getComment() != "" ? user.getComment() : newUser.getComment());
			userRepository.updateUser(newUser);
			return jwtTokenProvider.createToken(newUser);
		} else {
			throw new CustomException("User doesnt exist", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	public String updatePassword(String username, User user) {
		Optional<User> userDB = userRepository.findOne(username);
		if(user.getPassword() != "") {
			if (userDB.isPresent()) {
				User newUser = userDB.get();
				newUser.setPassword(passwordEncoder.encode(user.getPassword()));
				userRepository.updateUser(newUser);
				return jwtTokenProvider.createToken(newUser);
			} else {
				throw new CustomException("User doesnt exist", HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} else {
			throw new CustomException("Password doesnt valid", HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
	}
}
