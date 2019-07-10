package com.vodafone.eatwithrandom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.vodafone.eatwithrandom.model.Mesa;
import com.vodafone.eatwithrandom.repository.MesaRepository;

@Repository
public class MesaRepositoryImpl implements MesaRepository{

    private final MongoOperations mongoOperations;

    @SuppressWarnings("deprecation")
	@Autowired
    public MesaRepositoryImpl(MongoOperations mongoOperations) {
        Assert.notNull(mongoOperations);
        this.mongoOperations = mongoOperations;
    }
    
    //Find all users
    public List<Mesa> findAllPerDate(String hora) {
    	List<Mesa> mesas = this.mongoOperations.find(new Query(Criteria.where("horarios").in(hora)), Mesa.class);
        Optional<List<Mesa>> optionalMesa = Optional.ofNullable(mesas);
        return optionalMesa.get();
	}    

}