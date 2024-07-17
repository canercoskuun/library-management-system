package com.staj.demo.service;


import com.staj.demo.model.User;
import com.staj.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {


    private UserRepository userRepository;
    private JavaMailSender javaMailSender;
    public User addUser(User user) {
        sendMail(user);
        return userRepository.save(user);
    }



    private void sendMail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Library-Management-System");
        mailMessage.setText(user.getEmail() +" adresi ile başarılı bir şekilde kayıt oldunuz.");
        mailMessage.setFrom("canercoskun51@gmail.com");

        javaMailSender.send(mailMessage);
        System.out.println("Mail gönderildi");
    }

}
