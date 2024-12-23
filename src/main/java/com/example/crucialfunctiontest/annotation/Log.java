package com.example.crucialfunctiontest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author xushupeng
 * @Date 2024-12-23 13:12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)  // 注解作用在方法上
public @interface Log {
}
