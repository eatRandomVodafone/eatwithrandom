
package com.vodafone.eatwithrandom.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "reservaGrupal")
@JsonPropertyOrder({"reservaGrupalId", "fecha", "userId", "mesaId"})
public class ReservaGrupal implements Serializable{
	
	@Id
    private String reservaGrupalId;
    @NotNull    
    private LocalDateTime fecha;
    @NotNull    
    private String[] userId;
    @NotNull    
    private String mesaId;
	public String getReservaGrupalId() {
		return reservaGrupalId;
	}
	public void setReservaGrupalId(String reservaGrupalId) {
		this.reservaGrupalId = reservaGrupalId;
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
	public String getIdMesa() {
		return mesaId;
	}
	public void setIdMesa(String mesaId) {
		this.mesaId = mesaId;
	}
}