package com.example.email.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "email.use-real-smtp", havingValue = "true", matchIfMissing = false)
public class SmtpEmailSender implements EmailSender {
    private final JavaMailSender mailSender;
    private final Logger logger = LoggerFactory.getLogger(SmtpEmailSender.class);

    public SmtpEmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(String to, String from, String content) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setFrom(from);
        msg.setSubject("Notification");
        msg.setText(content);
        mailSender.send(msg);
        logger.info("SMTP mail sent to {}", to);
    }
}
