package com.vodafone.eatwithrandom.service;

import com.vodafone.eatwithrandom.exception.CustomException;
import com.vodafone.eatwithrandom.model.User;
import com.vodafone.eatwithrandom.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vodafone.eatwithrandom.utils.UserModel;


@Service
public class RecoverPwdService {

    @Autowired
    private UserRepositoryImpl userRepository;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    @Autowired
    private UserModel userModel;

    public String generatePassword() {
        StringBuilder builder = new StringBuilder();
        Integer count = 9;
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        String newPwd = builder.toString();
        
        String username = userModel.getCurrentUser().getUsername();

        User user = this.userRepository.findOne(username).orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
        user.setPassword(newPwd);

        this.userRepository.saveUser(user);

        return newPwd;
    }
}
