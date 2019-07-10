package com.vodafone.eatwithrandom.repository;

import com.vodafone.eatwithrandom.model.PoolGrupal;
import com.vodafone.eatwithrandom.model.User;

import java.util.List;
import java.util.Optional;

public interface PoolGrupalRepository{

	/**
     * save a new user
     * @param user
     * @return
     */
    public void savePoolGrupal(PoolGrupal pool);

    /**
     * Delete a user by id
     * @param userId
     */
    public void deletePoolGrupal(String userId);
    
    /**
     * Find a user     
     * @param user
     * @return
     */
	Optional<List<PoolGrupal>> findAllPerDate(String hora);	
    
}