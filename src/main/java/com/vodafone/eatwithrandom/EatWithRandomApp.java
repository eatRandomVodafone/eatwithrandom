package com.vodafone.eatwithrandom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class EatWithRandomApp {

  public static void main(String[] args) {
    SpringApplication.run(EatWithRandomApp.class, args);
  }
  

}
