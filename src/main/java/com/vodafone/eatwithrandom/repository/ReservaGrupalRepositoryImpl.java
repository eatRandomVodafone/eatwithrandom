package com.vodafone.eatwithrandom.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    
    public Optional<ReservaGrupal> findByUser(String userId){
    	Date now = new Date();
		String day = (1900+now.getYear()) + "/" + (1+now.getMonth()) + "/" + now.getDate();
		
    	ReservaGrupal d = this.mongoOperations.findOne(new Query(Criteria.where("userId").is(userId).and("fecha").regex(day)), ReservaGrupal.class);
    	Optional<ReservaGrupal> reservaGrupal = Optional.ofNullable(d);
        return reservaGrupal;

    }
    
    public void saveReserva(ReservaGrupal reserva) {
    	this.mongoOperations.save(reserva);
    }

}