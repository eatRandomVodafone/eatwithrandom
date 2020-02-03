package com.vodafone.eatwithrandom.security;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.vodafone.eatwithrandom.exception.CustomException;
import com.vodafone.eatwithrandom.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

  /**
   * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key here. Ideally, in a
   * microservices environment, this key would be kept on a config-server.
   */
  @Value("${security.jwt.token.secret-key:secret-key}")
  private String secretKey;

  @Value("${security.jwt.token.expire-length:3600000}")
  private long validityInMilliseconds = 3600000; // 1h

  @Autowired
  private MyUserDetails myUserDetails;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createToken(User user) {

    Claims claims = Jwts.claims().setSubject(user.getUsername());
    claims.put("usr", user.getUsername());
    claims.put("sec", user.getPassword());
    claims.put("name", user.getName());
    claims.put("department", user.getDepartment());
    claims.put("role", user.getRole());
    claims.put("status", user.getStatus());
    claims.put("userId", user.getUserId());
    
    if (user.getComment() != null && !user.getComment().isEmpty()) {
    	claims.put("comment", user.getComment());
    }

    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()//
        .setClaims(claims)//
        .setIssuedAt(now)//
        .setExpiration(validity)//
        .signWith(SignatureAlgorithm.HS256, secretKey)//
        .compact();
  }
 

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }
  
  public User getUser(String token) {        
      User user = new User();
      Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
      
      user.setUsername(claims.get("usr").toString());
      user.setPassword(claims.get("sec").toString());
      user.setName(claims.get("name").toString());
      user.setDepartment(claims.get("department").toString());
      user.setRole(claims.get("role").toString());
      user.setStatus(claims.get("status").toString());
      user.setUserId(claims.get("userId").toString());
      
      String aux = new String();      
      
      if (claims.get("comment") != null) {
    	  aux = (String) claims.get("comment");
    	  user.setComment(aux);
      }
      
      return user;
  }

  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
  
  public String resolveToken(String bearerToken) {
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
  
  public String updateToken(String bearerToken, HashMap<String, Object> claimsMap) {
	  
	  	Date now = new Date();
	    Date validity = new Date(now.getTime() + validityInMilliseconds);
	    
		String jwt = resolveToken(bearerToken);

		Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
		claims.putAll(claimsMap);

		String jwtUpdated = Jwts.builder()//
	        .setClaims(claims)//
	        .setIssuedAt(now)//
	        .setExpiration(validity)//
	        .signWith(SignatureAlgorithm.HS256, secretKey)//
	        .compact();

		return jwtUpdated;
	}

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
