package com.staj.demo.service;

import com.staj.demo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmailService {
    private JavaMailSender javaMailSender;
    public void sendMail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Library-Management-System");
        mailMessage.setText("Lütfen kitabınızı gün içinde iade ediniz.");
        mailMessage.setFrom("canercoskun51@gmail.com");
        javaMailSender.send(mailMessage);
        System.out.println("Mail gönderildi");
    }

}
