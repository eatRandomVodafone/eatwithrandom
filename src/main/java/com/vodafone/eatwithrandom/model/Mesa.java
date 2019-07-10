
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
    private String horarios;

	public Mesa(String mesaId, int maxPersonasMesa, String horarios) {
		this.mesaId = mesaId;
		this.maxPersonasMesa = maxPersonasMesa;
		this.horarios = horarios;
	}

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
	public String getHorarios() {
		return horarios;
	}
	public void setHorarios(String horarios) {
		this.horarios = horarios;
	}  

}
