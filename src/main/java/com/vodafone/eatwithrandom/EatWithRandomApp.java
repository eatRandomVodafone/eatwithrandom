package com.vodafone.eatwithrandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.vodafone.eatwithrandom.service.UserService;

@SpringBootApplication
public class EatWithRandomApp {

  public static void main(String[] args) {
    SpringApplication.run(EatWithRandomApp.class, args);
  }
  


}
