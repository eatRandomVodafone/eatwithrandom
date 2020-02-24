
package com.vodafone.eatwithrandom.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@Document(collection = "feedback")
public class Feedback implements Serializable{

	private static final long serialVersionUID = -6707461743822696114L;
	
	@Id
    private String feedbackId;
	@NotNull
    private String reservaGrupalId;
    @NotNull
    private Integer stars;
    @NotNull
    private String comment;
    
    
	public String getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getReservaGrupalId() {
		return reservaGrupalId;
	}
	public void setReservaGrupalId(String reservaGrupalId) {
		this.reservaGrupalId = reservaGrupalId;
	}
	public Integer getStars() {
		return stars;
	}
	public void setStars(Integer stars) {
		this.stars = stars;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
      
}
