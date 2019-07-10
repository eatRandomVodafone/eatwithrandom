package com.vodafone.eatwithrandom.repository;

import com.vodafone.eatwithrandom.model.PoolGrupal;
import com.vodafone.eatwithrandom.model.User;

import java.util.List;
import java.util.Optional;

public interface PoolGrupalRepository{

	public User saveUserPoolGroup(PoolGrupal userGroup);
    
    public void deleteUserPoolGroup(String userId);
    
}