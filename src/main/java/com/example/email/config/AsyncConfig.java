package com.example.email.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncConfig {
    // Default async configuration; add ThreadPoolTaskExecutor if desired.
}
