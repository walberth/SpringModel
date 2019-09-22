package com.project.xxxxx.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {
    @Before("execution(* com.project.xxxxx.service.implementation.*.*(..))")
    public void before(JoinPoint joinPoint) {
        System.setProperty("methodName", joinPoint.getSignature().getName());
    }
}
