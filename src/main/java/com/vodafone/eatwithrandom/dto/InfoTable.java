
package com.vodafone.eatwithrandom.dto;

import com.vodafone.eatwithrandom.model.User;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class InfoTable {
	
	@NotNull    
    private String idMesa;
	@NotNull
	private String date;
	@NotNull
	private ArrayList<User> users;

	public String getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(String idMesa) {
		this.idMesa = idMesa;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> userIds) {
		this.users = userIds;
	}
}
