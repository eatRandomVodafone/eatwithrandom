
package com.vodafone.eatwithrandom.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

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
