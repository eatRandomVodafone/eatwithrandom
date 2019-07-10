package com.vodafone.eatwithrandom.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vodafone.eatwithrandom.model.Config;
import com.vodafone.eatwithrandom.model.Mesa;
import com.vodafone.eatwithrandom.model.PoolGrupal;
import com.vodafone.eatwithrandom.model.ReservaGrupal;
import com.vodafone.eatwithrandom.model.User;
import com.vodafone.eatwithrandom.repository.ConfigRepository;
import com.vodafone.eatwithrandom.repository.MesaRepository;
import com.vodafone.eatwithrandom.repository.PoolGrupalRepository;
import com.vodafone.eatwithrandom.repository.ReservaGrupalRepository;
import com.vodafone.eatwithrandom.repository.UserRepository;
import com.vodafone.eatwithrandom.security.JwtTokenProvider;

@Service
public class AssignService {
	
	@Autowired
    private UserContextService userModel;
	
	@Autowired
	private PoolGrupalRepository poolGrupalRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReservaGrupalRepository reservaGrupalRepository;
	
	@Autowired
	private ConfigRepository configRepository;
	
	@Autowired
	private MesaRepository mesaRepository;
	
	@Autowired
    private JwtTokenProvider jwtTokenProvider;
	
	public void asignarMesa() {
		
		Date now = new Date();
		String day = (1900+now.getYear()) + "/" + (1+now.getMonth()) + "/" + now.getDate();

		
		Optional<List<Config>> config = configRepository.getConfig();
		if (config.isPresent()) {
			
			Integer min_grupo = Integer.parseInt(config.get().get(0).getMinPersonasGrupo());
			ArrayList<Mesa> mesas = config.get().get(0).getMesas();
			
			//13h
			Optional<List<PoolGrupal>> usersPool = poolGrupalRepository.findByHour("13h");
			if (usersPool.isPresent()) {
				List<PoolGrupal> usuarios = usersPool.get();
				ArrayList<Mesa> mesas13 = (ArrayList<Mesa>) mesas.stream()
						.filter(m -> m.getHorarios().equals("13h")
				).collect(Collectors.toList());
				
				
				Integer capacidad = mesas13.stream()
						.map(x -> x.getMaxPersonasMesa())
						.reduce(0, (a, b) -> a + b);
				
				//Cuando usuarios son mayores que la capacidad, voy llenando mesa a mesa
				if (usuarios.size() >= capacidad) {
					Integer max_mesa = 0;
					int i = 0;
					for (int j=0; j<mesas13.size();j++) {
						max_mesa = mesas13.get(j).getMaxPersonasMesa();
						ReservaGrupal reservaMesa = new ReservaGrupal();
						reservaMesa.setFecha(day+" 13h");
						reservaMesa.setIdMesa(mesas13.get(j).getIdmesa());
						ArrayList<String> usuariosReserva = new ArrayList<String>();
						for(; i<usuarios.size();i++) {
							if (usuariosReserva.size()< max_mesa)
								usuariosReserva.add(usuarios.get(i).getUserId());
							else
								break;
						}
						reservaMesa.setUserId(usuariosReserva);
						mesaRepository.saveReserva(reservaMesa);
					}
				}
				else {
					//Cuando tengo menos
				}
						
				
			}
			
			
			//14h
			usersPool = poolGrupalRepository.findByHour("14h");
			if (usersPool.isPresent()) {
				List<PoolGrupal> usuarios = usersPool.get();
				ArrayList<Mesa> mesas14 = (ArrayList<Mesa>) mesas.stream()
						.filter(m -> m.getHorarios().equals("14h")
				).collect(Collectors.toList());
				
				
				Integer capacidad = mesas14.stream()
						.map(x -> x.getMaxPersonasMesa())
						.reduce(0, (a, b) -> a + b);
				
				//Cuando usuarios son mayores que la capacidad, voy llenando mesa a mesa
				if (usuarios.size() >= capacidad) {
					Integer max_mesa = 0;
					int i = 0;
					for (int j=0; j<mesas14.size();j++) {
						max_mesa = mesas14.get(j).getMaxPersonasMesa();
						ReservaGrupal reservaMesa = new ReservaGrupal();
						reservaMesa.setFecha(day+" 14h");
						reservaMesa.setIdMesa(mesas14.get(j).getIdmesa());
						ArrayList<String> usuariosReserva = new ArrayList<String>();
						for(; i<usuarios.size();i++) {
							if (usuariosReserva.size()< max_mesa)
								usuariosReserva.add(usuarios.get(i).getUserId());
							else
								break;
						}
						reservaMesa.setUserId(usuariosReserva);
						mesaRepository.saveReserva(reservaMesa);
					}
				}
				else {
					//Cuando tengo menos
				}
						
				
			}
			
		}
		

	}

}
