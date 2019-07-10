
package com.vodafone.eatwithrandom.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

@Document(collection = "config")
public class Config {

    @NotNull    
    private String min_grupo;
    @NotNull    
    private ArrayList<Mesa> mesas;
    @NotNull
    private ArrayList<String> horarios;

	public String getMinPersonasGrupo() {
		return min_grupo;
	}
	public void setMinPersonasGrupo(String minPersonasGrupo) {
		this.min_grupo = minPersonasGrupo;
	}
	public ArrayList<Mesa> getMesas() {
		return mesas;
	}
	public void setMesas(ArrayList<Mesa> mesas) {
		this.mesas = mesas;
	}
	public ArrayList<String> getHorarios() {
		return horarios;
	}
	public void setHorarios(ArrayList<String> horarios) {
		this.horarios = horarios;
	}
    
}
