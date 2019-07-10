
package com.vodafone.eatwithrandom.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

@Document(collection = "poolGrupal")
@JsonPropertyOrder({"userId", "horaComida"})
public class PoolGrupal implements Serializable{

    @NotNull    
    private String userId;
    @NotNull    
    private LocalDateTime horaComida;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public LocalDateTime getHoraComida() {
		return horaComida;
	}
	public void setHoraComida(LocalDateTime horaComida) {
		this.horaComida = horaComida;
	}
	
    
}
