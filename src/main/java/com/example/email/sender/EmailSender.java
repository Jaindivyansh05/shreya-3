package com.example.email.sender;

public interface EmailSender {
    void send(String to, String from, String content);
}
