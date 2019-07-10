package com.vodafone.eatwithrandom.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.vodafone.eatwithrandom.model.ReservaGrupal;

@Repository
public interface ReservaGrupalRepository{
    
	Optional<ReservaGrupal> findByUser(String userId);


}