
package com.vodafone.eatwithrandom.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

@Document(collection = "mesa")
@JsonPropertyOrder({"mesaId", "maxPersonasMesa", "horarios"})

public class Mesa {
	
	@NotNull    
    private String mesaId;
    @NotNull    
    private int maxPersonasMesa;
    @NotNull    
    private LocalDateTime[] horarios;
	public String getMesaId() {
		return mesaId;
	}
	public void setMesaId(String mesaId) {
		this.mesaId = mesaId;
	}
	public int getMaxPersonasMesa() {
		return maxPersonasMesa;
	}
	public void setMaxPersonasMesa(int maxPersonasMesa) {
		this.maxPersonasMesa = maxPersonasMesa;
	}
	public LocalDateTime[] getHorarios() {
		return horarios;
	}
	public void setHorarios(LocalDateTime[] horarios) {
		this.horarios = horarios;
	}  

}
