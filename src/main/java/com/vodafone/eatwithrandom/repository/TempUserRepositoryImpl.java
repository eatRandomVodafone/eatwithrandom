package com.vodafone.eatwithrandom.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.vodafone.eatwithrandom.model.TempUser;
import com.vodafone.eatwithrandom.utils.RandomString;

@Repository
public class TempUserRepositoryImpl implements TempUserRepository{

    private final MongoOperations mongoOperations;
    
    @SuppressWarnings("deprecation")
	@Autowired
    public TempUserRepositoryImpl(MongoOperations mongoOperations) {
        Assert.notNull(mongoOperations);
        this.mongoOperations = mongoOperations;
    }

    public String saveTempUser (String jwt) {
    	String token = RandomString.generateString();

        TempUser tempuser = new TempUser();
        tempuser.setJwt(jwt);
        tempuser.setToken(token);
    	this.mongoOperations.save(tempuser, "tempusers");
    	
    	return token;
    }
    
    public Optional<TempUser> getTempUser (String token) {
    	TempUser d = this.mongoOperations.findOne(new Query(Criteria.where("token").is(token)), TempUser.class);
    	Optional<TempUser> tempUser = Optional.ofNullable(d);    	
    	return tempUser;
    }
    
    public void deleteTempUser (TempUser tempuser ) {
    	String jwt = tempuser.getJwt();
    	String token = tempuser.getToken();
    	
    	this.mongoOperations.findAndRemove(new Query(Criteria.where("token").is(token).and("jwt").is(jwt)), TempUser.class);
    }
    
}