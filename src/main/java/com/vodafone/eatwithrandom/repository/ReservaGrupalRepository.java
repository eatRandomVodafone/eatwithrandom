package com.vodafone.eatwithrandom.repository;

import com.vodafone.eatwithrandom.model.ReservaGrupal;

public interface ReservaGrupalRepository{

       
    /**
     * create a new reservaGrupal
     * @param reservaGrupal
     * @return
     */
    public void createReservaGrupal(ReservaGrupal reserva);

    /**
     * Update a reservaGrupal
     * @param reservaGrupal
     */
    public void includeUser(ReservaGrupal reserva);

    
}