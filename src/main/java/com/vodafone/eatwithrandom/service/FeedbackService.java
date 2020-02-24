package com.vodafone.eatwithrandom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vodafone.eatwithrandom.enums.subjectsEmail;
import com.vodafone.eatwithrandom.model.Feedback;
import com.vodafone.eatwithrandom.model.ReservaGrupal;
import com.vodafone.eatwithrandom.repository.FeedbackRepository;
import com.vodafone.eatwithrandom.repository.ReservaGrupalRepository;
import com.vodafone.eatwithrandom.repository.UserRepository;

@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private ReservaGrupalRepository reservaGrupalRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
	public void sendFeedback() {
		List<ReservaGrupal> listReservaGrupal = reservaGrupalRepository.findAll().get();
		
		for(ReservaGrupal reservaGrupal : listReservaGrupal) {
			for(String userId : reservaGrupal.getUserId()) {
				//TODO: Falta crear el html y ver si necesitamos sustituir valores
				String email = userRepository.findById(userId).get().getUsername();
				emailService.sendEmail(subjectsEmail.FEEDBACK.toString(), email, "feedback.html", null);
			}
		}		
	}
	
	public void saveFeedback(Feedback feedback) {
		feedbackRepository.saveFeedback(feedback);		
	}
	
}
