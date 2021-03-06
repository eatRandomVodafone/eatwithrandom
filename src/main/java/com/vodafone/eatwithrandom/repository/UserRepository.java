package com.vodafone.eatwithrandom.repository;

import java.util.List;
import java.util.Optional;

import com.vodafone.eatwithrandom.model.TempUser;
import com.vodafone.eatwithrandom.model.User;

public interface UserRepository{

       
   /**
    * Find a users list     
    * @return
    */
  Optional<List<User>> findAll();

    /**
     * Find a user
     * @param username
     * @return
     */
    public Optional<User> findOne(String username);

    /**
     * save a new user
     * @param user
     * @return
     */
    public User saveUser(User user);

    /**
     * Update a user
     * @param user
     */
    public void updateUser(User user);

    /**
     * Delete a user by id
     * @param userId
     */
    public void deleteUser(String userId);
    
    /**
     * 
     * @param username
     * @param password
     * @return
     */
    public Optional<User> checkPassword (String username, String password);
    
    /**
     * 
     * @param jwt
     * @return
     */
    public String saveTempUser(String jwt);
    
    /**
     * 
     * @param token
     * @return
     */
    public Optional<TempUser> getTempUser(String token);
    
    /**
     * 
     * @param tempuser
     */
    public void deleteTempUser(TempUser tempuser);
    
    /**
     * 
     * @param userId
     * @return
     */
    public Optional<User> findById(String userId);


}