
package com.vodafone.eatwithrandom.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@Document(collection = "feedback")
public class Feedback implements Serializable{

	private static final long serialVersionUID = -6707461743822696114L;
	
	@NotNull
    private String reservaGrupalId;
    @NotNull
    private String stars;
    @NotNull
    private String comment;
	public String getReservaGrupalId() {
		return reservaGrupalId;
	}
	public void setReservaGrupalId(String reservaGrupalId) {
		this.reservaGrupalId = reservaGrupalId;
	}
	public String getStars() {
		return stars;
	}
	public void setStars(String stars) {
		this.stars = stars;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
      
}
