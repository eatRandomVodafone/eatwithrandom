package com.vodafone.eatwithrandom.repository;

import java.util.List;

import com.vodafone.eatwithrandom.model.Mesa;

public interface MesaRepository{

       
   /**
    * Find a mesa list     
     * @param user
     * @return
    */
  List<Mesa> findAllPerDate(String hora);


}