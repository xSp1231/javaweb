package com.example.crucialfunctiontest.controller;

import com.example.crucialfunctiontest.Aop.MyLog;
import com.example.crucialfunctiontest.annotation.Log;
import com.example.crucialfunctiontest.model.dto.PageParam;
import com.example.crucialfunctiontest.model.entity.User;
import com.example.crucialfunctiontest.model.vo.PageResult;
import com.example.crucialfunctiontest.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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


    @Resource
    private UserService userService;

    @GetMapping("/page/{currentPage}/{pageSize}")
    public PageResult<User> page(@PathVariable("currentPage") Integer currentPage,
                       @PathVariable("pageSize") Integer pageSize){
        //构造分页参数
        PageParam pageParam=new PageParam(currentPage,pageSize);
        // 调用service层
        PageResult<User> pageResult=userService.getUsersByPage(pageParam);

        return pageResult;
    }


}
