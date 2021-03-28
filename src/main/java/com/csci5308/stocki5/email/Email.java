package com.csci5308.stocki5.email;

import com.csci5308.stocki5.config.Stocki5AppEmailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Email implements IEmail
{
	@Autowired
	Stocki5AppEmailConfig configEmail;

	@Override
	public boolean sendEmail(String toEmail, String subject, String content)
	{
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(toEmail);
		email.setText(content);
		email.setSubject(subject);

		JavaMailSender mailSender = configEmail.configureJavaMail();
		mailSender.send(email);
		return true;
	}
}