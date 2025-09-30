package com.example.email.service;

import com.example.email.sender.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final EmailSender emailSender;
    private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    public EmailServiceImpl(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    @Async
    public void sendEmailAsync(String to, String from, String content) {
        logger.info("Submitting email send task for {}", to);
        try {
            emailSender.send(to, from, content);
            logger.info("Email send finished for {}", to);
        } catch (Exception e) {
            logger.error("Failed to send email to {}: {}", to, e.getMessage(), e);
        }
    }
}
