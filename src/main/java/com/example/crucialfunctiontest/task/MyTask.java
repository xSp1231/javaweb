package com.example.crucialfunctiontest.task;

import com.alibaba.fastjson.JSON;
import com.example.crucialfunctiontest.server.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author xushupeng
 * @Date 2025-01-04 14:34
 */
@Component
@Slf4j
public class MyTask {

    @Autowired
    private WebSocketServer webSocketServer;
    @Scheduled(cron = "0/5 * * * * ?") //每隔五秒执行一次
    public void sendData(){
        //System.out.println("sendData");

        //发送一个数组 转为json字符串

        webSocketServer.sendAll("服务端发来数据hello world(所有人可以接收)");

        List<Integer> randomIntegers = new ArrayList<>();
        Random random = new Random();

        // 生成长度为 7 的随机整数列表
        for (int i = 0; i < 7; i++) {
            randomIntegers.add(random.nextInt(1000)); // 生成 0-99 之间的随机整数
        }
        String clientName="forerunner";
        //转为json字符串
        // 将 List 转换为 JSON 字符串
        String jsonString = JSON.toJSONString(randomIntegers);
        System.out.println(jsonString);
        webSocketServer.sendMessageTo(clientName,jsonString);

    }
}
