package com.vodafone.eatwithrandom.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // Disable CSRF (cross site request forgery)
    http.csrf().disable();
    
    // Enable CORS
    http.cors();

    // No session will be created or used by spring security
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Entry points
    http.authorizeRequests()//
        .antMatchers(HttpMethod.POST, "/eatwithrandom/signin").permitAll()//
        .antMatchers(HttpMethod.POST, "/eatwithrandom/signup").permitAll()//
        .antMatchers(HttpMethod.GET, "/eatwithrandom/recoverpwd").permitAll()//
        .antMatchers(HttpMethod.GET, "/eatwithrandom/postsignup/**").permitAll()//
        .antMatchers(HttpMethod.POST, "/eatwithrandom/asignar").permitAll()//
        .antMatchers(HttpMethod.GET, "/eatwithrandom/config").permitAll()//
        .antMatchers(HttpMethod.POST, "/eatwithrandom/saveFeedback").permitAll()//
        // Disallow everything else..
        .anyRequest().authenticated().and()
        .apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

    // If a user try to access a resource without having enough permissions
    http.exceptionHandling().accessDeniedPage("/login");


    // Optional, if you want to test the API from a browser
    // http.httpBasic();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

}
