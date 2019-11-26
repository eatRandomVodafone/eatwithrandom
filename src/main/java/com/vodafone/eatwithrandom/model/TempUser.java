
package com.vodafone.eatwithrandom.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@Document(collection = "tempusers")
public class TempUser implements Serializable{

	private static final long serialVersionUID = -5937373645204642129L;
	
	@NotNull
    private String jwt;
    @NotNull
    private String token;
    
    
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}    
}
