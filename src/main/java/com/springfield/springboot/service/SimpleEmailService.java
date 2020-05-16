package com.springfield.springboot.service;

import com.springfield.springboot.model.ResetToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailService")
public class SimpleEmailService implements EmailService {
    private JavaMailSender javaMailSender;
    @Autowired
    public SimpleEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private static final Logger logger = LoggerFactory.getLogger(SimpleEmailService.class);

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }
    @Override
    public void sendResetToken(String email, ResetToken resetToken) {
        // create the email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Password Reset Link");
        mailMessage.setFrom("SpringfieldSSMS@gmail.com");
        mailMessage.setText("If you have requested that your password by reset, please click here: "
                +"http://localhost:8080/confirm-reset?token="+ resetToken.getValue());

        logger.debug(String.format("Sending Reset Email to %s", email));
        sendEmail(mailMessage);
    }

}