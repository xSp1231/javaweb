package com.example.crucialfunctiontest.operateLogTest;

import com.example.crucialfunctiontest.model.entity.OperateLog;
import com.example.crucialfunctiontest.mapper.OperateLogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Author xushupeng
 * @Date 2024-12-23 14:12
 */
@SpringBootTest
public class OperateLogTest {


    @Resource
    private OperateLogMapper OperateLogMapper;
    @Test
    public void test(){

        OperateLog operateLog = new OperateLog();
        operateLog.setOperateUser(1);
        LocalDateTime now=LocalDateTime.now();
        System.out.println("now is "+now);
        operateLog.setOperateTime(now);
        operateLog.setClassName("TestClass");
        operateLog.setMethodName("testMethod");
        operateLog.setMethodParams("param1,param2");
        operateLog.setReturnValue("success");
        // 保存日志
        OperateLogMapper.insertLog(operateLog);


    }

}
