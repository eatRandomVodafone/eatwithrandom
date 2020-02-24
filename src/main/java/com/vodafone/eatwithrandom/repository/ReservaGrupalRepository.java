package com.vodafone.eatwithrandom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.vodafone.eatwithrandom.model.ReservaGrupal;

@Repository
public interface ReservaGrupalRepository{
    
	public Optional<ReservaGrupal> findByUser(String userId);

    public void saveReserva(ReservaGrupal reserva);
    
    public Optional<List<ReservaGrupal>> findAll();

}