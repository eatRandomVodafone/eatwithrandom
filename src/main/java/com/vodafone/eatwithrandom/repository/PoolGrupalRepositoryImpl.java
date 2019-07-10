package com.vodafone.eatwithrandom.repository;

import com.vodafone.eatwithrandom.repository.UserRepository;
import com.vodafone.eatwithrandom.model.Mesa;
import com.vodafone.eatwithrandom.model.PoolGrupal;
import com.vodafone.eatwithrandom.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Optional;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Repository
public class PoolGrupalRepositoryImpl implements PoolGrupalRepository{

    private final MongoOperations mongoOperations;

    @SuppressWarnings("deprecation")
	@Autowired
    public PoolGrupalRepositoryImpl(MongoOperations mongoOperations) {
        Assert.notNull(mongoOperations);
        this.mongoOperations = mongoOperations;
    }
    
    public PoolGrupal saveUserPoolGroup(PoolGrupal userGroup) {
        this.mongoOperations.save(userGroup);
        return findBy(userGroup.getUserId(),"userId").get();
    }
    
    public void deleteUserPoolGroup(String userId) {
    	this.mongoOperations.findAndRemove(new Query(Criteria.where("userId").is(userId)), PoolGrupal.class);
    }
    
    public Optional<PoolGrupal> findBy(String search, String field) {
    	PoolGrupal d = this.mongoOperations.findOne(new Query(Criteria.where(field).is(search)), PoolGrupal.class);
        Optional<PoolGrupal> poolGrupal = Optional.ofNullable(d);
        return poolGrupal;
    }
    
    public Optional<PoolGrupal> findUser(String userId) {
    	PoolGrupal d = this.mongoOperations.findOne(new Query(Criteria.where("userId").is(userId)), PoolGrupal.class);
    	Optional<PoolGrupal> poolGrupal = Optional.ofNullable(d);
        return poolGrupal;
    }
}