package com.vodafone.eatwithrandom.repository;

import java.util.List;
import java.util.Optional;

import com.vodafone.eatwithrandom.model.PoolGrupal;
import com.vodafone.eatwithrandom.model.ReservaGrupal;
import com.vodafone.eatwithrandom.model.User;

public interface PoolGrupalRepository{

	/**
	 * 
	 * @param userGroup
	 * @return
	 */
	public PoolGrupal saveUserPoolGroup(PoolGrupal userGroup);
    
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
    public Optional<PoolGrupal> findBy(String search, String field);
    
    /**
     * 
     * @param userId
     * @return
     */
    public Optional<PoolGrupal> findUser(String userId);
    

    /**
     * 
     * @param hour
     * @return
     */
    public Optional<List<PoolGrupal>> findByHour(String hour);
    
    /**
     * 
     */
    public void deleteAll();
    
    /**
     * 
     * @param reserva
     */
    public void saveReserva(ReservaGrupal reserva);

    
}