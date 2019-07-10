
package com.vodafone.eatwithrandom.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

@Document(collection = "reservaF2F")
@JsonPropertyOrder({"reservaF2FId", "fecha", "userId", "userIdNext"})
public class ReservaF2F implements Serializable{
	
	@Id
    private String reservaF2FId;
    @NotNull    
    private LocalDateTime fecha;
    @NotNull    
    private String[] userId;
    @NotNull    
    private String[] userIdNext;
    
	public String getReservaF2FId() {
		return reservaF2FId;
	}
	public void setReservaF2FId(String reservaF2FId) {
		this.reservaF2FId = reservaF2FId;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public String[] getUserId() {
		return userId;
	}
	public void setUserId(String[] userId) {
		this.userId = userId;
	}
	public String[] getUserIdNext() {
		return userIdNext;
	}
	public void setUserIdNext(String[] userIdNext) {
		this.userIdNext = userIdNext;
	}
    
    
	
}
