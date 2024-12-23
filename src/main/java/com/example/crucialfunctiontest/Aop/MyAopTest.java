package com.example.crucialfunctiontest.Aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Author xushupeng
 * @Date 2024-12-23 11:26
 */
@Slf4j
@Aspect
@Component
public class MyAopTest {
    //基于自定义注解实现特定方法匹配

    //@Before("execution(* com.example.crucialfunctiontest.controller.*.*(..))")
    @Before("@annotation(com.example.crucialfunctiontest.Aop.MyLog)")
    public void before() {  //只要方法上加的有自定义注解 那么都会执行该方法 功能增强
        log.info("@MyLog before");
    }


    @Around("@annotation(com.example.crucialfunctiontest.Aop.MyLog)")

    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("MyAspect around before ...");

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("目标类的类名: {}", className);
        log.info("目标方法的名称: {}", methodName);

        Object result = joinPoint.proceed();

        log.info("MyAspect around after ...");
        return result;


    }


}
