
package com.vodafone.eatwithrandom.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "reservaGrupal")
@JsonPropertyOrder({"reservaGrupalId", "fecha", "userId", "idMesa"})
public class ReservaGrupal implements Serializable{
	
	private static final long serialVersionUID = -1582625775933912690L;
	
	@Id
    private String reservaGrupalId;
    @NotNull    
    private String fecha;
    @NotNull    
    private ArrayList<String> userId;
    @NotNull    
    private String idMesa;
    
	public String getReservaGrupalId() {
		return reservaGrupalId;
	}
	public void setReservaGrupalId(String reservaGrupalId) {
		this.reservaGrupalId = reservaGrupalId;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public ArrayList<String> getUserId() {
		return userId;
	}
	public void setUserId(ArrayList<String> userId) {
		this.userId = userId;
	}
	public String getIdMesa() {
		return idMesa;
	}
	public void setIdMesa(String idMesa) {
		this.idMesa = idMesa;
	}   
}
