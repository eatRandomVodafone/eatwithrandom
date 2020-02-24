package com.vodafone.eatwithrandom.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.vodafone.eatwithrandom.exception.CustomException;

@Service
public class EmailService {

	@Value("${mail.remitenteMail:eatrandomvodafone}")
	private String remitente = "";

	@Value("${mail.passwordMail:bxjetfbreodfsvtz}")
	private String password;

	public void sendEmail(String subject, String email, String template, ArrayList<String> values) {		

		String body = createEmail(template, values.toArray(new String[values.size()]));

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", password); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); // Se podrían añadir varios de
																						// la misma manera
			message.setSubject(subject);
			message.setContent(body, "text/html");
			Transport t = session.getTransport("smtp");
			t.connect("smtp.gmail.com", (String) props.get("mail.smtp.user"), password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	public String createEmail(String template, String[] values) {

		try {
			String pathFile = "classpath:template/" + template;
			String html = "";
			File file = ResourceUtils.getFile(pathFile);
			FileReader f = new FileReader(file);
			BufferedReader b = new BufferedReader(f);
			StringBuilder htmltmp = new StringBuilder();
			while ((html = b.readLine()) != null) {
				htmltmp.append(html);
			}

			html = MessageFormat.format(htmltmp.toString(), values);
			b.close();
			return html;
		} catch (IOException e) {
			throw new CustomException("Error sending email to confirm signup", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
