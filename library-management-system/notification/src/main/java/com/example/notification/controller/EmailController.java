package com.example.notification.controller;


import com.example.notification.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> sendEmail(@RequestParam String receiver) {
        try {
            emailService.sendMail(receiver);
            return ResponseEntity.ok("Email gönderildi");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
}


