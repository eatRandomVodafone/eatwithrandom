
package com.vodafone.eatwithrandom.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

@Document(collection = "mesa")
public class Mesa {
	
	@NotNull    
    private String idMesa;
    @NotNull    
    private int max;
    @NotNull    
    private String horario;


	public String getIdmesa() {
		return idMesa;
	}
	public void setIdmesa(String idMesa) {
		this.idMesa = idMesa;
	}
	public int getMaxPersonasMesa() {
		return max;
	}
	public void setMaxPersonasMesa(int max) {
		this.max = max;
	}
	public String getHorarios() {
		return horario;
	}
	public void setHorarios(String horario) {
		this.horario = horario;
	}  

}
