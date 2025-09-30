package com.example.email.service;

import com.example.email.sender.EmailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class EmailServiceTest {

    private EmailSender emailSender;
    private EmailServiceImpl emailService;

    @BeforeEach
    void setUp() {
        emailSender = Mockito.mock(EmailSender.class);
        emailService = new EmailServiceImpl(emailSender);
    }

    @Test
    void whenSendEmailAsync_thenDelegatesToSender() {
        String to = "a@ex.com";
        String from = "b@ex.com";
        String content = "hello";

        emailService.sendEmailAsync(to, from, content);

        ArgumentCaptor<String> toCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> fromCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> contentCaptor = ArgumentCaptor.forClass(String.class);

        verify(emailSender).send(toCaptor.capture(), fromCaptor.capture(), contentCaptor.capture());

        assertEquals(to, toCaptor.getValue());
        assertEquals(from, fromCaptor.getValue());
        assertEquals(content, contentCaptor.getValue());
    }
}
