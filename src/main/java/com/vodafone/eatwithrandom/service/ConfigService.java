package com.vodafone.eatwithrandom.service;

import com.vodafone.eatwithrandom.model.Config;
import com.vodafone.eatwithrandom.model.Mesa;
import com.vodafone.eatwithrandom.repository.ConfigRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConfigService {
	
	@Autowired
	private ConfigRepository configRepository;


    public Config readConfig() {
    	    	
		Optional<List<Config>> config = configRepository.getConfig();
		if (config.isPresent()) {
			return config.get().get(0);
		}
		
		return null;
    }

    public List<Mesa> readMesasByHour(String horario) {
    	return this.readConfig().getMesas().stream().filter(m -> m.getHorarios().equals(horario)).collect(Collectors.toList());
	}

    public int getMaxCapacidadTotalByHour(String horario) {
    	List<Mesa> mesas = this.readMesasByHour(horario);
    	return mesas.stream().mapToInt(Mesa::getMaxPersonasMesa).sum();
	}
}
