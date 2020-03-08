package com.vodafone.eatwithrandom.service;

import com.vodafone.eatwithrandom.model.Config;
import com.vodafone.eatwithrandom.model.Mesa;
import com.vodafone.eatwithrandom.repository.ConfigRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConfigService {
	
	@Autowired
	private ConfigRepository configRepository;


    public Config readConfig() {
    	    	
		Optional<List<Config>> config = configRepository.getConfig();
        return config.map(configs -> configs.get(0)).orElse(null);

    }

    public List<Mesa> readMesasByHour(String horario) {
    	return this.readConfig().getMesas()
				.stream()
				.filter(m -> m.getHorarios().equals(horario))
				.sorted(Comparator.comparing(Mesa::getMaxPersonasMesa))
				.collect(Collectors.toList());
	}

    public int getMaxCapacidadTotalByHour(String horario) {
    	List<Mesa> mesas = this.readMesasByHour(horario);
    	return mesas.stream().mapToInt(Mesa::getMaxPersonasMesa).sum();
	}

	public int getCapacidadByIdMesa(String mesaId) {
		return this.readConfig().getMesas()
				.stream()
				.filter(m -> m.getIdmesa().equals(mesaId))
				.collect(Collectors.toList())
				.get(0)
				.getMaxPersonasMesa();
	}
}
