package com.example.notification.controller;


import com.example.notification.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/email")
public class EmailController {
    private EmailService emailService;

    // Email gönderme işlemi

    @PostMapping("/send")
    public boolean sendEmail(@RequestParam String receiver) {
        try {
            emailService.sendMail(receiver);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
