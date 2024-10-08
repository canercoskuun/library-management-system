package com.example.notification.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String receiver) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(receiver);
        mailMessage.setSubject("Library-Management-System");
        mailMessage.setText("Lütfen kitabınızı gün içinde iade ediniz.");
        mailMessage.setFrom("canercoskun51@gmail.com");
        javaMailSender.send(mailMessage);
        System.out.println("Mail gönderildi");
    }

}
