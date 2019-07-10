package com.vodafone.eatwithrandom.repository;

import com.vodafone.eatwithrandom.model.User;


import java.util.List;
import java.util.Optional;

public interface MesaRepository{

       
   /**
    * Find a mesa list     
     * @param user
     * @return
    */
  Optional <List<User>> findAllPerDate(String hora);


}