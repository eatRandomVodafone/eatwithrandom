package com.vodafone.eatwithrandom.repository;

import java.util.Optional;

import com.vodafone.eatwithrandom.model.Feedback;

public interface FeedbackRepository{

	public Feedback saveFeedback(Feedback feedback);
	
	public Optional<Feedback> findBy(String search, String field);
    
}