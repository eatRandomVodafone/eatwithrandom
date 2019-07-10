package com.vodafone.eatwithrandom.repository;

import com.vodafone.eatwithrandom.repository.ReservaGrupalRepository;
import com.vodafone.eatwithrandom.model.User;
import com.vodafone.eatwithrandom.model.ReservaGrupal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Repository
public class ReservaGrupalRepositoryImpl implements ReservaGrupalRepository{

    private final MongoOperations mongoOperations;

    @SuppressWarnings("deprecation")
	@Autowired
    public UserRepositoryImpl(MongoOperations mongoOperations) {
        Assert.notNull(mongoOperations);
        this.mongoOperations = mongoOperations;
    }
    
    public ReservaGrupal createReservaGrupal(ReservaGrupal reserva) {
        this.mongoOperations.save(reserva);
        return findOne(reserva.getIdMesa()).get();
    }
    
    public void includeUser(ReservaGrupal reserva) {
        this.mongoOperations.save(reserva);
    }
}