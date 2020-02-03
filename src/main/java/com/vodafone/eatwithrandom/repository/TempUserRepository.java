package com.vodafone.eatwithrandom.repository;

import java.util.Optional;

import com.vodafone.eatwithrandom.model.TempUser;

public interface TempUserRepository{

       
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
      

}