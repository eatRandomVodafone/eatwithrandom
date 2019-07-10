package com.vodafone.eatwithrandom.repository;

import com.vodafone.eatwithrandom.model.ReservaGrupal;
import com.vodafone.eatwithrandom.model.User;

import java.util.List;
import java.util.Optional;

public interface ReservaGrupalRepository{

       
    /**
     * create a new reservaGrupal
     * @param reservaGrupal
     * @return
     */
    public ReservaGrupal createReservaGrupal(ReservaGrupal reserva);

    /**
     * Update a reservaGrupal
     * @param reservaGrupal
     */
    public void includeUser(ReservaGrupal reserva);

    
}