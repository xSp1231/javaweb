package com.example.crucialfunctiontest.Aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author xushupeng
 * @Date 2024-12-23 11:28
 */

@Retention(RetentionPolicy.RUNTIME) // 运行时才生效
@Target(ElementType.METHOD)  // 注解作用在方法上
public @interface MyLog {

}
