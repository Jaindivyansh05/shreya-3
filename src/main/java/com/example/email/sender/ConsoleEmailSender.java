package com.example.email.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "email.use-real-smtp", havingValue = "false", matchIfMissing = true)
public class ConsoleEmailSender implements EmailSender {
    private final Logger logger = LoggerFactory.getLogger(ConsoleEmailSender.class);

    @Override
    public void send(String to, String from, String content) {
        logger.info("[ConsoleEmailSender] Sending email to: {}, from: {}, content: {}", to, from, content);
        // Simulate latency
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
