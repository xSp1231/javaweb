package com.example.crucialfunctiontest.mapper;

import com.example.crucialfunctiontest.model.entity.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author xushupeng
 * @Date 2024-12-23 13:01
 */
@Mapper
public interface OperateLogMapper {


    int insertLog(OperateLog operateLog);

    @Insert("insert into operate_log(operateUser,operateTime,className,methodName,methodParams,returnValue) values(#{operateUser},#{operateTime},#{className},#{methodName},#{methodParams},#{returnValue})")
    int inserteLog1(OperateLog operateLog);
}
