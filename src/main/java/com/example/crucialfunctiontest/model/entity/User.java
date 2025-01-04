package com.example.crucialfunctiontest.model.entity;

import lombok.Data;

/**
 * @Author xushupeng
 * @Date 2024-12-20 22:28
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private int gender;
    private String birthday;
    private String job;
}

