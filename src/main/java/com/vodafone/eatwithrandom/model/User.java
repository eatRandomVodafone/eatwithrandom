
package com.vodafone.eatwithrandom.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vodafone.eatwithrandom.enums.Status;

@Document(collection = "users")
@JsonPropertyOrder({"userId", "username", "password", "name", "department", "role", "comment", "status"})
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private String department;
    @NotNull    
    private String role;    
    @NotNull    
    private String status = Status.STANDBY.toString();
    
    private String comment;
    
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
