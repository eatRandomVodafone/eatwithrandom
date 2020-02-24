package com.vodafone.eatwithrandom.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.vodafone.eatwithrandom.model.Feedback;

@Repository
public class FeedbackRepositoryImpl implements FeedbackRepository{

    private final MongoOperations mongoOperations;

    @SuppressWarnings("deprecation")
	@Autowired
    public FeedbackRepositoryImpl(MongoOperations mongoOperations) {
        Assert.notNull(mongoOperations);
        this.mongoOperations = mongoOperations;
    }
    
    public Feedback saveFeedback(Feedback feedback) {
        this.mongoOperations.save(feedback);
        return findBy(feedback.getFeedbackId(),"feedbackId").get();
    }
    
    public Optional<Feedback> findBy(String search, String field) {
    	Feedback d = this.mongoOperations.findOne(new Query(Criteria.where(field).is(search)), Feedback.class);
        Optional<Feedback> feedback = Optional.ofNullable(d);
        return feedback;
    }
}