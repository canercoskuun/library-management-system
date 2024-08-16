package com.example.notification.controller;


import com.example.notification.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

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

    @GetMapping("/test")
    public String test() {
        return "Test";
    }
}


