package com.vodafone.eatwithrandom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vodafone.eatwithrandom.exception.CustomException;
import com.vodafone.eatwithrandom.model.User;
import com.vodafone.eatwithrandom.repository.UserRepositoryImpl;


@Service
public class RecoverPwdService {

    @Autowired
    private UserRepositoryImpl userRepository;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    @Autowired
    private EmailService emailService;

    public String generatePassword(String email) {
        StringBuilder builder = new StringBuilder();
        Integer count = 9;
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        String newPwd = builder.toString();
        
//        String username = userModel.getCurrentUser().getUsername();

        User user = this.userRepository.findOne(email).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
        user.setPassword(newPwd);

        this.userRepository.saveUser(user);
                
        emailService.sendEmail("Reseteo de contraseña", "Tu nueva contraseña es: " + newPwd, email);

        return newPwd;
    }
    
    
}
