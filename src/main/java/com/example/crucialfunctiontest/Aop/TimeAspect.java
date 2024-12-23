package com.example.crucialfunctiontest.Aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author xushupeng
 * @Date 2024-12-23 9:51
 */
@Component
//@Aspect  // AOP类
@Slf4j
public class TimeAspect {
    @Around("execution(* com.example.crucialfunctiontest.controller.*.*(..))")
    //表示执行com.example.crucialfunctiontest.Aop包下所有的接口或者类里面的所有方法时, 都会执行该Aop逻辑
    // execution(* com.example.crucialfunctiontest.Aop.*.*(..)) 切点表达式
    // ProceedingJoinPoint表示当前正在执行的方法
    public Object recordTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object object = proceedingJoinPoint.proceed(); // 调用原方法执行
        long end = System.currentTimeMillis();
        log.info(proceedingJoinPoint.getSignature() + " 执行耗时: " + (end - begin) + "ms");
        return object;
    }
    //在所有方法执行之前执行
    @Before("execution(* com.example.crucialfunctiontest.controller.*.*(..))")
    public void before() {
        log.info("before");
    }
    // 在所有方法执行之后执行
    @After("execution(* com.example.crucialfunctiontest.controller.*.*(..))")
    public void after() {
        log.info("after");
    }
    // 在方法正常执行之后执行
    @AfterReturning("execution(* com.example.crucialfunctiontest.controller.*.*(..))")
    public void afterReturning() {
        log.info("afterReturning");
    }
    // 在方法出现异常时执行
    @AfterThrowing("execution(* com.example.crucialfunctiontest.controller.*.*(..))")
    public void afterThrowing() {
        log.info("afterThrowing");
    }


}