package com.vodafone.eatwithrandom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.vodafone.eatwithrandom.model.TempUser;
import com.vodafone.eatwithrandom.model.User;
import com.vodafone.eatwithrandom.utils.RandomString;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final MongoOperations mongoOperations;

    @SuppressWarnings("deprecation")
	@Autowired
    public UserRepositoryImpl(MongoOperations mongoOperations) {
        Assert.notNull(mongoOperations);
        this.mongoOperations = mongoOperations;
    }
    
    //Find all users
    public Optional<List<User>> findAll() {
    	List<User> users = this.mongoOperations.find(new Query(), User.class);
        Optional<List<User>> optionalUsers = Optional.ofNullable(users);
        return optionalUsers;
	}    

    public Optional<User> findOne(String username) {
        User d = this.mongoOperations.findOne(new Query(Criteria.where("username").is(username)), User.class);
        Optional<User> user = Optional.ofNullable(d);
        return user;
    }
    
    public Optional<User> findById(String userId) {
        User d = this.mongoOperations.findOne(new Query(Criteria.where("userId").is(userId)), User.class);
        Optional<User> user = Optional.ofNullable(d);
        return user;
    }

    public User saveUser(User user) {
        this.mongoOperations.save(user);
        return findOne(user.getUsername()).get();
    }
    
    public void updateUser(User user) {
        this.mongoOperations.save(user);
    }

    public void deleteUser(String username) {
        this.mongoOperations.findAndRemove(new Query(Criteria.where("username").is(username)), User.class);
    }
    
    //Check username & password
    public Optional<User> checkPassword (String username, String password) {
        User d = this.mongoOperations.findOne(new Query(Criteria.where("username").is(username).and("password").is(password)), User.class);
        Optional<User> user = Optional.ofNullable(d);
        return user;
    }
    
    public String saveTempUser (String jwt) {
    	String token = RandomString.generateString();

        TempUser tempuser = new TempUser();
        tempuser.setJwt(jwt);
        tempuser.setToken(token);
    	this.mongoOperations.save(tempuser);
    	
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