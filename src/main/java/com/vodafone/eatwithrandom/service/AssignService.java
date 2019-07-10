package com.vodafone.eatwithrandom.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vodafone.eatwithrandom.model.Config;
import com.vodafone.eatwithrandom.model.Mesa;
import com.vodafone.eatwithrandom.model.PoolGrupal;
import com.vodafone.eatwithrandom.model.ReservaGrupal;
import com.vodafone.eatwithrandom.model.User;
import com.vodafone.eatwithrandom.repository.ConfigRepository;
import com.vodafone.eatwithrandom.repository.PoolGrupalRepository;
import com.vodafone.eatwithrandom.repository.UserRepository;

@Service
public class AssignService {
	
	@Autowired
	private PoolGrupalRepository poolGrupalRepository;
	
	@Autowired
	private ConfigRepository configRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserRepository userRepository;
	
	public void asignarMesa() {
		
		Date now = new Date();
		String day = (1900+now.getYear()) + "/" + (1+now.getMonth()) + "/" + now.getDate();

		
		Optional<List<Config>> config = configRepository.getConfig();
		if (config.isPresent()) {
			
			Integer min_grupo = Integer.parseInt(config.get().get(0).getMinPersonasGrupo());
			ArrayList<Mesa> mesas = config.get().get(0).getMesas();
			ArrayList<String> horarios = config.get().get(0).getHorarios();
			
			for (String h : horarios) {
				Optional<List<PoolGrupal>> usersPool = poolGrupalRepository.findByHour(h);
				if (usersPool.isPresent()) {
					List<PoolGrupal> usuarios = usersPool.get();
					ArrayList<Mesa> mesasHora = (ArrayList<Mesa>) mesas.stream()
							.filter(m -> m.getHorarios().equals(h)
					).collect(Collectors.toList());
					
					
					Integer capacidad = mesasHora.stream()
							.map(x -> x.getMaxPersonasMesa())
							.reduce(0, (a, b) -> a + b);
					
					//Cuando usuarios son mayores que la capacidad, voy llenando mesa a mesa
					if (usuarios.size() >= capacidad) {
						Integer max_mesa = 0;
						int i = 0;
						for (int j=0; j<mesasHora.size();j++) {
							max_mesa = mesasHora.get(j).getMaxPersonasMesa();
							ReservaGrupal reservaMesa = new ReservaGrupal();
							reservaMesa.setFecha(day+" "+h);
							reservaMesa.setIdMesa(mesasHora.get(j).getIdmesa());
							ArrayList<String> usuariosReserva = new ArrayList<String>();
							for(; i<usuarios.size();i++) {
								if (usuariosReserva.size()< max_mesa)
									usuariosReserva.add(usuarios.get(i).getUserId());
								else
									break;
							}
							reservaMesa.setUserId(usuariosReserva);
							poolGrupalRepository.saveReserva(reservaMesa);
							
							//Envío mail a los usuarios asignados
							for(String userId : reservaMesa.getUserId()) {
								String email = userRepository.findById(userId).get().getUsername();
						        emailService.sendEmail("Mesa asignada", "Se te ha asignado una mesa para comer. ¡Revisa en la app con quién comes hoy! ", email);
							}
						}
					}
					else {
						//Cuando tengo menos de mi capacidad
					}
							
					
				}
			}

			//Borrado del poolGrupal
			poolGrupalRepository.deleteAll();
			
		}
		

	}

}
