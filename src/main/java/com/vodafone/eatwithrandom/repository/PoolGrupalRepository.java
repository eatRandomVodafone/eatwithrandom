package com.vodafone.eatwithrandom.repository;

import java.util.Optional;

import com.vodafone.eatwithrandom.model.PoolGrupal;
import com.vodafone.eatwithrandom.model.User;

public interface PoolGrupalRepository{

	/**
	 * 
	 * @param userGroup
	 * @return
	 */
	public User saveUserPoolGroup(PoolGrupal userGroup);
    
	/**
	 * 
	 * @param userId
	 */
    public void deleteUserPoolGroup(String userId);
    
    /**
     * 
     * @param search
     * @param field
     * @return
     */
    public Optional<User> findBy(String search, String field);
    
}