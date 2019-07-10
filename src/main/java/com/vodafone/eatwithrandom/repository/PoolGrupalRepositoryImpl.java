package com.vodafone.eatwithrandom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.vodafone.eatwithrandom.model.PoolGrupal;

@Repository
public class PoolGrupalRepositoryImpl implements PoolGrupalRepository{

    private final MongoOperations mongoOperations;

    @SuppressWarnings("deprecation")
	@Autowired
    public PoolGrupalRepositoryImpl(MongoOperations mongoOperations) {
        Assert.notNull(mongoOperations);
        this.mongoOperations = mongoOperations;
    }
    
    public void savePoolGrupal(PoolGrupal pool) {
        this.mongoOperations.save(pool);

    }
    
    public void deletePoolGrupal(String userId) {
        this.mongoOperations.findAndRemove(new Query(Criteria.where("userId").is(userId)), PoolGrupal.class);
    }
    
    //Find all users
    public Optional<List<PoolGrupal>> findAllPerDate(String hora) {
    	List<PoolGrupal> poolGrupal = this.mongoOperations.find(new Query(Criteria.where("horaComida").is(hora)), PoolGrupal.class);
        Optional<List<PoolGrupal>> optionalPoolGrupal = Optional.ofNullable(poolGrupal);
        return optionalPoolGrupal;
	}
}