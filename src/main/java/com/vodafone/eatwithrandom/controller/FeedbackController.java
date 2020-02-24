package com.vodafone.eatwithrandom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vodafone.eatwithrandom.model.Feedback;
import com.vodafone.eatwithrandom.service.FeedbackService;
import com.vodafone.eatwithrandom.service.UserService;


@RestController
@RequestMapping("/eatwithrandom")
@CrossOrigin(origins = "*")
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private UserService userService;
	
	//TODO: Rest de envio de email con encuesta para los comensales diarios - servicio cron diario (10)
	
	//TODO: Rest que guarda las valoraciones del usuario (11)
	
	@GetMapping("/feedback")
    public ResponseEntity<?> sendFeedback(){

		feedbackService.sendFeedback();
		userService.updateUserStatus();
    	return ResponseEntity.ok().build();
    }
	
	@PostMapping("/saveFeedback")
    public ResponseEntity<?> signup(@RequestBody Feedback feedback) {
    	
		feedbackService.saveFeedback(feedback);
    	return ResponseEntity.ok().build();
    }

}
