package com.springfield.springboot.service;

import com.springfield.springboot.model.ResetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailSenderService")
public class SimpleEmailService implements EmailService {
    private JavaMailSender javaMailSender;
    @Autowired
    public SimpleEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

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
        System.out.println("CLICKING SEND");
        sendEmail(mailMessage);
    }

}