package com.vodafone.eatwithrandom.repository;

import com.vodafone.eatwithrandom.model.PoolGrupal;
import com.vodafone.eatwithrandom.model.User;

import java.util.List;
import java.util.Optional;

public interface PoolGrupalRepository{

	/**
     * save a new user pool group
     * @param user
     * @return
     */
    public User saveUserPoolGroup(PoolGrupal userGroup);
    
    /**
     * Delete a user pool group by id
     * @param userId
     */
    public void deleteUserPoolGroup(String userId);
    
}