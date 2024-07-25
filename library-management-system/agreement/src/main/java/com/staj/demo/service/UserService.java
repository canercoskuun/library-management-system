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
    public User addUser(User user) {
        return userRepository.save(user);
    }





}
