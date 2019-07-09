
package net.mvcj.microservices.users.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@Document(collection = "users")
@JsonPropertyOrder({"userId", "name", "phone", "email"})
public class User implements Serializable{

	private static final long serialVersionUID = -7788619177798333712L;

    @Id
    private String userId;
    @NotNull    
    private String name;
    @NotNull    
    private String phone;
    @NotNull    
    private String email;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
