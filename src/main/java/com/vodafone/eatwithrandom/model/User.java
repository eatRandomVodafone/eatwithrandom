package com.vodafone.eatwithrandom.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@Document(collection = "users")
@JsonPropertyOrder({"username", "password"})
public class User implements Serializable{

	private static final long serialVersionUID = -7788619177798333712L;

    @Id
    private String username;
    @NotNull    
    private String password;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}