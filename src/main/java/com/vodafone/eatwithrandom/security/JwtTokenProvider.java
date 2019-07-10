package com.vodafone.eatwithrandom.security;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.vodafone.eatwithrandom.exception.CustomException;
import com.vodafone.eatwithrandom.model.User;

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
    claims.put("area", user.getArea());
    claims.put("rol", user.getRol());
    claims.put("bio", user.getBio());
    
    if (user.getAficiones() != null && !user.getAficiones().isEmpty()) {
    	claims.put("aficiones", user.getAficiones());
    }
    if (user.getAlergias() != null && !user.getAlergias().isEmpty()) {
    	claims.put("alergias", user.getAlergias());
    }
    if (user.getHoraPrefer() != null) {
    	claims.put("horaPrefer", user.getHoraPrefer());
    }
    if (user.getIdiomaPrefer() != null) {
    	claims.put("idiomaPrefer", user.getIdiomaPrefer());
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
      user.setArea(claims.get("area").toString());
      user.setRol(claims.get("rol").toString());
      user.setBio(claims.get("bio").toString());
      
      ArrayList<String> aux = new ArrayList<String>();      
      
      if (claims.get("aficiones") != null) {
    	  aux = (ArrayList) claims.get("aficiones");
    	  user.setAficiones(aux);
      }
      if (claims.get("alergias") != null) {
    	  aux = (ArrayList) claims.get("alergias");
    	  user.setAlergias(aux);
      }
      if (claims.get("horaPrefer") != null) {
    	  user.setHoraPrefer(claims.get("horaPrefer").toString());
      }
      if (claims.get("idiomaPrefer") != null) {
    	  user.setIdiomaPrefer(claims.get("idiomaPrefer").toString());
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
