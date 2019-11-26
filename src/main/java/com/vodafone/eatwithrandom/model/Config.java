
package com.vodafone.eatwithrandom.model;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config")
public class Config {

    @NotNull    
    private String minUserTable;
    @NotNull    
    private ArrayList<Mesa> mesas;
    @NotNull
    private ArrayList<String> horarios;
    

	public String getMinUserTable() {
		return minUserTable;
	}
	public void setMinUserTable(String minUserTable) {
		this.minUserTable = minUserTable;
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
