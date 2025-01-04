package com.example.crucialfunctiontest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.crucialfunctiontest.model.dto.PageParam;
import com.example.crucialfunctiontest.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author xushupeng
 * @Date 2024-12-20 22:34
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> getAllUser();

    //动态sql + 单参数传递(直接写)
    List<User> selectUserById(Integer id);


    //注解传递参数 适合于参数不多
    //#{}里面的名称对应的是注解@Param括号里面修饰的名称。 非常好


    List<User> selectUserByAnnotation(@Param("id") Integer id, @Param("username") String username);

    //实体类传参
    //#{}里面的名称对应的是User类里面的成员属性。
    //这种方法很直观，但需要建一个实体类，扩展不容易，需要加属性，看情况使用。
    List<User> selectUserByEntity(User user);

    //动态插入
    int insertUser(User user);

    int updateUser(User user);

//    choose(when,otherwise)语句
//    有时候，我们不想用到所有的查询条件，只想选择其中一个，查询条件有一个满足即可，使用choose标签可以解决此类问题，类似于Java的Switch语句
    // 通过choose查询学生信息
    List<User> selectUserByChoose(@Param("username") String name, @Param("gender") int gender, @Param("age") Integer age);



    //分页
    List<User> selectUsersByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("select count(*) from user")
    int countUsers();

}
