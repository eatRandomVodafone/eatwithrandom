
package com.vodafone.eatwithrandom.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

@Document(collection = "users")
@JsonPropertyOrder({"userId", "username", "password", "name", "area", "rol", "aficiones", "alergias", "idiomaPrefer", "horaPrefer", "bio", "nextDados", "asignaciones", "negativos"})
public class User implements Serializable{

	private static final long serialVersionUID = -7788619177798333712L;

    @Id
    private String userId;
    @NotNull
    private String username;
    @NotNull    
    private String password;
    @NotNull    
    private String name;
    @NotNull    
    private String area;
    @NotNull    
    private String rol;
        
    private String [] aficiones;
        
    private String [] alergias;
        
    private String horaPrefer;
        
    private String idiomaPrefer;
    @NotNull    
    private String bio;
    @NotNull    
    private int nextDados;
    @NotNull    
    private int asignaciones;
    @NotNull    
    private int negativos;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String[] getAficiones() {
		return aficiones;
	}
	public void setAficiones(String[] aficiones) {
		this.aficiones = aficiones;
	}
	public String[] getAlergias() {
		return alergias;
	}
	public void setAlergias(String[] alergias) {
		this.alergias = alergias;
	}
	public String getHoraPrefer() {
		return horaPrefer;
	}
	public void setHoraPrefer(String horaPrefer) {
		this.horaPrefer = horaPrefer;
	}
	public String getIdiomaPrefer() {
		return idiomaPrefer;
	}
	public void setIdiomaPrefer(String idiomaPrefer) {
		this.idiomaPrefer = idiomaPrefer;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public int getNextDados() {
		return nextDados;
	}
	public void setNextDados(int nextDados) {
		this.nextDados = nextDados;
	}
	public int getAsignaciones() {
		return asignaciones;
	}
	public void setAsignaciones(int asignaciones) {
		this.asignaciones = asignaciones;
	}
	public int getNegativos() {
		return negativos;
	}
	public void setNegativos(int negativos) {
		this.negativos = negativos;
	}
    
}
