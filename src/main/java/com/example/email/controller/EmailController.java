package com.example.email.controller;

import com.example.email.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    // Example: GET /recipient%40example.com/sender%40example.com/Hello%20there
    @GetMapping("{to}/{from}/{content}")
    public ResponseEntity<String> send(@PathVariable String to,
                                       @PathVariable String from,
                                       @PathVariable String content) {
        emailService.sendEmailAsync(to, from, content);
        return ResponseEntity.accepted().body("Email queued for " + to);
    }
}
