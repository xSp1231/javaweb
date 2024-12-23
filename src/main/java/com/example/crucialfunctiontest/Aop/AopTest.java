package com.example.crucialfunctiontest.Aop;

/**
 * @Author xushupeng
 * @Date 2024-12-23 10:00
 */
public class AopTest {

    static int getSum(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {

        System.out.println(getSum(1, 2));
    }

}
