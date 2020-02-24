package com.vodafone.eatwithrandom.service;

import java.util.*;
import java.util.stream.Collectors;

import com.vodafone.eatwithrandom.dto.InfoTable;
import com.vodafone.eatwithrandom.exception.CustomException;
import com.vodafone.eatwithrandom.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import com.vodafone.eatwithrandom.enums.subjectsEmail;
import com.vodafone.eatwithrandom.model.Config;
import com.vodafone.eatwithrandom.model.Mesa;
import com.vodafone.eatwithrandom.model.PoolGrupal;
import com.vodafone.eatwithrandom.model.ReservaGrupal;
import com.vodafone.eatwithrandom.repository.PoolGrupalRepository;
import com.vodafone.eatwithrandom.repository.ReservaGrupalRepository;
import com.vodafone.eatwithrandom.repository.UserRepository;

@Slf4j
@Service
public class AssignService {

	@Autowired
	private PoolService poolService;
	
	@Autowired
	private ReservaGrupalRepository reservaGrupalRepository;

	@Autowired
	private ConfigService configService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserContextService userContextService;

	private static final long ONE_MINUTE = 60 * 1000L;

	@Scheduled(cron = "0 0 12 * * MON-THU")
	public void init() {
		try {
			asignarMesa();
		} catch (Exception e) {
			//log.error("Error durante el proceso de asignación ");
		}
	}

	public void asignarMesa() {

		//log.info("Inicio proceso de asignación");

		Date now = new Date();
		String day = (1900 + now.getYear()) + "/" + (1 + now.getMonth()) + "/" + now.getDate();

		Config config = configService.readConfig();
		if (config != null) {

			Integer min_grupo = Integer.parseInt(config.getMin_grupo());
			ArrayList<String> horarios = config.getHorarios();

			for (String h : horarios) {
				List<PoolGrupal> pool = poolService.readUsersByHour(h);
				Integer max_capacidad = configService.getMaxCapacidadTotalByHour(h);

				if (pool != null) {
					Integer num_com = Math.min(pool.size(), max_capacidad);
					Integer num_mesas = num_com / min_grupo;
					this.minimunInsertion();

					//Cuando usuarios son mayores que la capacidad, voy llenando mesa a mesa
					// if (usuarios.size() >= capacidad) {
					// 	Integer max_mesa = 0;
					// 	int i = 0;
					// 	for (int j = 0; j < mesasHora.size(); j++) {
					// 		max_mesa = mesasHora.get(j).getMaxPersonasMesa();
					// 		ReservaGrupal reservaMesa = new ReservaGrupal();
					// 		reservaMesa.setFecha(day + " " + h);
					// 		reservaMesa.setIdMesa(mesasHora.get(j).getIdmesa());
					// 		ArrayList<String> usuariosReserva = new ArrayList<String>();
					// 		for (; i < usuarios.size(); i++) {
					// 			if (usuariosReserva.size() < max_mesa)
					// 				usuariosReserva.add(usuarios.get(i).getUserId());
					// 			else
					// 				break;
					// 		}
					// 		reservaMesa.setUserId(usuariosReserva);
					// 		reservaGrupalRepository.saveReserva(reservaMesa);

					// 		//Envío mail a los usuarios asignados
					// 		for (String userId : reservaMesa.getUserId()) {
					// 			//TODO: Falta crear el html y ver si necesitamos sustituir valores
					// 			String email = userRepository.findById(userId).get().getUsername();
					// 			emailService.sendEmail(subjectsEmail.ASSIGNTABLE.toString(), email, "assignTable.html", null);
					// 		}
					// 	}
					// } else {
					//Cuando tengo menos de mi capacidad
					// }


				}
			}

			//Borrado del poolGrupal
			poolService.deleteAll();

		}

			//log.info("Fin proceso de asignación");
	}

	private void minimunInsertion() {

	}

	public InfoTable readInfoTable() {
		User currentUser = userRepository.findOne(this.userContextService.getCurrentUser().getUsername())
				.orElseThrow(() -> new CustomException("Cannot find user (" + this.userContextService.getCurrentUser().getUsername() + ")", HttpStatus.NOT_FOUND));
		ReservaGrupal reservaGrupal = this.reservaGrupalRepository.findByUser(currentUser.getUserId())
				.orElseThrow(() -> new CustomException("No reservation found for (" + currentUser.getUserId() + ") user", HttpStatus.NOT_FOUND));

		InfoTable infoTable = new InfoTable();
		infoTable.setIdMesa(reservaGrupal.getIdMesa());
		infoTable.setDate(reservaGrupal.getFecha());

		ArrayList<User> users = new ArrayList<>();
		reservaGrupal.getUserId().forEach(userId -> {
			User user = userRepository.findById(userId)
					.orElseThrow(() -> new CustomException("Cannot find users for (" + reservaGrupal.getReservaGrupalId() + ") reservation", HttpStatus.NOT_FOUND));
			user.setUserId(null);
			user.setPassword(null);
			user.setStatus(null);
			infoTable.setUsers(users);
			users.add(user);
		});

		return infoTable;
	}
}
