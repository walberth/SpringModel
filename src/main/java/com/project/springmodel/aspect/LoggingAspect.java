package com.project.springmodel.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {
    @Before("execution(* com.project.springmodel.service.implementation.*.*(..))")
    public void before(JoinPoint joinPoint) {
        System.setProperty("methodName", joinPoint.getSignature().getName());
    }
}
