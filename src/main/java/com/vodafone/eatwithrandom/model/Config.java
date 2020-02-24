
package com.vodafone.eatwithrandom.model;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config")
public class Config {

    @NotNull    
    private String min_grupo;
    @NotNull    
    private ArrayList<Mesa> mesas;
    @NotNull
    private ArrayList<String> horarios;

	public String getMin_grupo() {
		return min_grupo;
	}

	public void setMin_grupo(String min_grupo) {
		this.min_grupo = min_grupo;
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
