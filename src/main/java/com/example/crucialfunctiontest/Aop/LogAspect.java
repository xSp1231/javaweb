package com.example.crucialfunctiontest.Aop;

import com.example.crucialfunctiontest.model.entity.OperateLog;
import com.example.crucialfunctiontest.mapper.OperateLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Author xushupeng
 * @Date 2024-12-23 13:13
 */

@Component
@Aspect   //切面类
@Slf4j
public class LogAspect {
    @Resource
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.example.crucialfunctiontest.annotation.Log)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        Integer operateUser=666;
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        log.info("当前时间: {}", now);

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        //获取参数
        Object[] args = joinPoint.getArgs();
        // 参数转为字符串
        String methodParams = Arrays.toString(args);

        log.info("目标类的类名: {}", className);
        log.info("目标方法的名称: {}", methodName);
        log.info("参数: {}",methodParams);

        Long start= System.currentTimeMillis();


        Object result = joinPoint.proceed(); //方法执行


        Long end= System.currentTimeMillis();
        long totalTime = end - start;
        //将对象转为字符串
        String returnValue = result.toString();
        log.info("执行时间"+totalTime);
        operateLogMapper.insertLog(
                new OperateLog(null,
                        operateUser,now,
                        className,methodName,
                        methodParams,returnValue)
        );

        return result;
    }

}
