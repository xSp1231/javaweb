package com.example.crucialfunctiontest.controller;

import com.example.crucialfunctiontest.Aop.MyLog;
import com.example.crucialfunctiontest.annotation.Log;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xushupeng
 * @Date 2024-12-23 10:03
 */
@RestController
@CrossOrigin
public class TestController {
    @Log
    @GetMapping("/test")
    public String test(){
//        double a=1/0;
        return "test";
    }

    //使用自定义注解@MyLog标记
    @Log
    @GetMapping("/test1")
    public String test1(@RequestParam("username")String username){
        System.out.println("username is "+username);
//        double a=1/0;
        return "HELLO";

    }
    @MyLog
    @GetMapping("/test2")
    public String test2(){
//        double a=1/0;
        return "test2";
    }
}
