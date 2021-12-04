package tn.esprit.spring.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
@Service
public class EmailSenderService {
	 @Autowired
	    private JavaMailSender mailSender;
	 
	 
	 	

	    public void sendSimpleEmail(String toEmail,
	                                String body,
	                                String subject) throws MessagingException {
	    	MimeMessage message= mailSender.createMimeMessage();
	    	MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
	        message.setFrom("ahmedbenhamida290@gmail.com");
	        helper.setTo(toEmail);
	        message.setContent(body , "text/html");
	        message.setSubject(subject);
	        

	        mailSender.send(message);
	        System.out.println("Mail Send...");
	    }

	    public void sendEmailWithAttachment(String toEmail,
	                                        String body,
	                                        String subject) throws MessagingException {

	        MimeMessage mimeMessage = mailSender.createMimeMessage();

	        MimeMessageHelper mimeMessageHelper
	                = new MimeMessageHelper(mimeMessage, true);

	        mimeMessageHelper.setFrom("spring.email.from@gmail.com");
	        mimeMessageHelper.setTo(toEmail);
	        mimeMessageHelper.setText(body);
	        mimeMessageHelper.setSubject(subject);

	        mailSender.send(mimeMessage);
	        System.out.println("Mail Send...");

	    }
	}

