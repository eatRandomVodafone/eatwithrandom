package com.vodafone.eatwithrandom.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/eatwithrandom")
@CrossOrigin(origins = "*")
public class FeedbackController {
	
	//TODO: Rest de envio de email con encuesta para los comensales diarios - servicio cron diario (10)
	
	//TODO: Rest que guarda las valoraciones del usuario (11)

}
