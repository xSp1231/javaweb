package com.example.crucialfunctiontest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrucialFunctionTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrucialFunctionTestApplication.class, args);
        System.out.println("启动!");
    }

}
