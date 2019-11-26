
package com.vodafone.eatwithrandom.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "poolGrupal")
@JsonPropertyOrder({"userId", "horaComida"})
public class PoolGrupal implements Serializable{

	private static final long serialVersionUID = -600296445414732290L;
	
	@NotNull    
    private String userId;
    @NotNull    
    private String hour;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}    
}
