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
import com.vodafone.eatwithrandom.model.ReservaF2F;

@Repository
public class ReservaF2FRepositoryImpl implements ReservaF2FRepository{

	private final MongoOperations mongoOperations;

    @SuppressWarnings("deprecation")
	@Autowired
    public ReservaF2FRepositoryImpl(MongoOperations mongoOperations) {
        Assert.notNull(mongoOperations);
        this.mongoOperations = mongoOperations;
    }
    
    public void createReservaF2F(ReservaF2F reserva) {
        this.mongoOperations.save(reserva);

    }
    
    public void includeUser(ReservaF2F reserva) {
        this.mongoOperations.save(reserva);
    }
    
    public void deleteUserReservaF2F(String userId) {
        this.mongoOperations.findAndRemove(new Query(Criteria.where("userId").is(userId)), PoolGrupal.class);
    }
    
    public Optional<List<ReservaF2F>> findAllPerDate(String userId) {
    	List<ReservaF2F> reservaF2F = this.mongoOperations.find(new Query(Criteria.where("userId").is(userId)), ReservaF2F.class);
        Optional<List<ReservaF2F>> optionalReservaF2F = Optional.ofNullable(reservaF2F);
        return optionalReservaF2F;
	}
}