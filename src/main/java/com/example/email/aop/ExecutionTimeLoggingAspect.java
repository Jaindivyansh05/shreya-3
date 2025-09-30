package com.example.email.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeLoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(ExecutionTimeLoggingAspect.class);

    @Around("execution(* com.example.email.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long elapsed = System.currentTimeMillis() - start;
            logger.info("[AOP] {} executed in {} ms", joinPoint.getSignature(), elapsed);
        }
    }
}
