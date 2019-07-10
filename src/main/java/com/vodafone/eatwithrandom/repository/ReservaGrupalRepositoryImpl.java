package com.vodafone.eatwithrandom.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.vodafone.eatwithrandom.model.ReservaGrupal;

@Repository
public class ReservaGrupalRepositoryImpl implements ReservaGrupalRepository{

	private final MongoOperations mongoOperations;

    @SuppressWarnings("deprecation")
	@Autowired
    public ReservaGrupalRepositoryImpl(MongoOperations mongoOperations) {
        Assert.notNull(mongoOperations);
        this.mongoOperations = mongoOperations;
    }
    
    public void createReservaGrupal(ReservaGrupal reserva) {
    	this.mongoOperations.save(reserva);
    }
    
    public void includeUser(ReservaGrupal reserva) {
        this.mongoOperations.save(reserva);
    }
}