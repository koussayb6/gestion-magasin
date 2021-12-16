package com.esprit.springjwt.security.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.esprit.springjwt.models.User;



@Service
public class MailService {

	

	/*
	 * The Spring Framework provides an easy abstraction for sending email by
	 * using the JavaMailSender interface, and Spring Boot provides
	 * auto-configuration for it as well as a starter module.
	 */
	private JavaMailSender javaMailSender;

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmail1(String Email,String Username) throws MailException {

		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(Email);
		mail.setSubject("Testing Mail API");
		mail.setText("Dear Client"+Username+"Welcome to our Site ");

		javaMailSender.send(mail);
	}


	
	public void sendEmailWithAttachment(User user) throws MailException, MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setTo(user.getEmail());
		helper.setSubject("Testing Mail API with Attachment");
		helper.setText("Please find the attached document below.");

		ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);

		javaMailSender.send(mimeMessage);
	}
}
