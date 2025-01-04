package com.example.crucialfunctiontest.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author xushupeng
 * @Date 2024-12-23 13:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateLog {
    private Integer id; // ID
    private Integer operateUser; // 操作用户ID
    private LocalDateTime operateTime; // 操作时间
    private String className; // 操作类名
    private String methodName; // 操作方法名
    private String methodParams; // 操作方法参数
    private String returnValue; // 操作方法返回值
}