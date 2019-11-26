package com.vodafone.eatwithrandom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.vodafone.eatwithrandom.model.Config;

@Repository
public class ConfigRepositoryImpl implements ConfigRepository{

    private final MongoOperations mongoOperations;

    @SuppressWarnings("deprecation")
	@Autowired
    public ConfigRepositoryImpl(MongoOperations mongoOperations) {
        Assert.notNull(mongoOperations);
        this.mongoOperations = mongoOperations;
    }
    
    public Optional<List<Config>> getConfig() {
        List<Config> d = this.mongoOperations.find(new Query(), Config.class);
        Optional<List<Config>> config = Optional.ofNullable(d);
        return config;
    }
}