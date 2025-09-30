package com.example.email.controller;

import com.example.email.service.EmailService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = EmailController.class)
public class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @Test
    void whenGetEndpoint_thenReturns202AndCallsService() throws Exception {
        String to = "a%40ex.com"; // URL-encoded '@'
        String from = "b%40ex.com";
        String message = "hello";

        mockMvc.perform(get("/" + to + "/" + from + "/" + message))
                .andExpect(status().isAccepted())
                .andExpect(content().string("Email queued for " + "a%40ex.com"));

        // The controller passes the raw path segment (URL-decoded by Spring). To verify call, use decoded values:
        verify(emailService).sendEmailAsync("a@ex.com", "b@ex.com", "hello");
    }
}
