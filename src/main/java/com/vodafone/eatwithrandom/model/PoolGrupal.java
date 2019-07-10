
package com.vodafone.eatwithrandom.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "poolGrupal")
@JsonPropertyOrder({"userId", "horaComida"})
public class PoolGrupal implements Serializable{

    @NotNull    
    private String userId;
    @NotNull    
    private String horaComida;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHoraComida() {
		return horaComida;
	}
	public void setHoraComida(String horaComida) {
		this.horaComida = horaComida;
	}
	
    
}
