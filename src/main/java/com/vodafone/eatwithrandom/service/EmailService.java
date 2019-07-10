package com.vodafone.eatwithrandom.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
	
	@Value("${mail.remitenteMail:eatrandomvodafone}")
	private String remitente = "";
	
	@Value("${mail.passwordMail:Vodafone2019}")
	private String password;
	
    
    public void sendEmail(String subject, String body, String email) {

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", password);    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));   //Se podrían añadir varios de la misma manera
            message.setSubject(subject);
            message.setText(body);
            Transport t = session.getTransport("smtp");
			t.connect("smtp.gmail.com", (String)props.get("mail.smtp.user"), password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
//            Transport.send(message);
        }
        catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }
    }
}
