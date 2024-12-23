package com.example.crucialfunctiontest.UserTest;

import com.example.crucialfunctiontest.entity.User;
import com.example.crucialfunctiontest.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author xushupeng
 * @Date 2024-12-20 22:47
 */
@SpringBootTest
public class UserOperateLogTestDemo {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test(){
    userMapper.getAllUser().forEach(it->{
        System.out.println(it);
    });

}
@Test
public void selectTest(){

        //userMapper.selectUserById(2);
//        String a="pp";
//    System.out.println(userMapper.selectUserByAnnotation(1,a ));
    User user=new User();

    user.setUsername("xsp");
    user.setPassword("123456");
    user.setAge(22);
    user.setGender(1);
    user.setBirthday("2002-11-01");
    user.setJob("后端开发");
    // userMapper.updateUser(user);
    //userMapper.selectUserByEntity(user);
    userMapper.selectUserByAnnotation(1,"鹏");

    //chose 使用
    //userMapper.selectUserByChoose(null,1,null);
     //userMapper.insertUser(user);
 }

}
