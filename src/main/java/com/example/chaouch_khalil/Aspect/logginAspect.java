package com.example.chaouch_khalil.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
@Slf4j
public class logginAspect {



        @After("execution(void com.example.chaouch_khalil.Controller.*.*(..))")
        public void logMethodEntry(JoinPoint joinPoint) {

            String name = joinPoint.getSignature().getName();

            log.info( name );
        }
}
