package com.daysurprise.study.guava.interfaceIntercept;

import java.util.concurrent.ExecutionException;

/**
 * @Class: com.daysurprise.study.guava.interfaceIntercept.TestCounter
 * @Author: daysurprise
 * @Date: 2021/1/29
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class TestCounter {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    System.out.println(Counter.getCounter());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
