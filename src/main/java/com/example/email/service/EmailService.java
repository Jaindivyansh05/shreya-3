package com.example.email.service;

public interface EmailService {
    void sendEmailAsync(String to, String from, String content);
}
