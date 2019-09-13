package com.project.xxxxx.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Aspect
@Configuration
public class RestConfiguration implements WebMvcConfigurer {
    @Autowired
    private RequestInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }

    @Before("execution(* com.project.xxxxx.service.implementation.*.*(..))")
    public void before(JoinPoint joinPoint) {
        System.setProperty("methodName", joinPoint.getSignature().getName());
    }
}
