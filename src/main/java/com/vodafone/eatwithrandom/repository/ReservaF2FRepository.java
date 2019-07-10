package com.vodafone.eatwithrandom.repository;

import com.vodafone.eatwithrandom.model.ReservaF2F;
import com.vodafone.eatwithrandom.model.User;

import java.util.List;
import java.util.Optional;

public interface ReservaF2FRepository{

       
    /**
     * create a new reservaF2F
     * @param reservaF2F
     * @return
     */
    public ReservaF2F createReservaF2F(ReservaF2F reserva);

    /**
     * Update a reservaF2F
     * @param reservaF2F
     */
    public void includeUser(ReservaF2F reserva);

    /**
     * Delete a user by id
     * @param userId
     */
    public void deleteUserReservaF2F(String userId);
    
    /**
     * Find a user list     
      * @param user
      * @return
     */
    public Optional<List<ReservaF2F>> findAllPerDate(String userId);
}