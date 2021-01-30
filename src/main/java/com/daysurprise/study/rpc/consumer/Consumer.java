package com.daysurprise.study.rpc.consumer;

import com.daysurprise.study.rpc.framework.ProxyFactory;
import com.daysurprise.study.rpc.provider.HelloService;

/**
 * @Class: com.daysurprise.study.rpc.consumer.Consumer
 * @Author: daysurprise
 * @Date: 2021/1/28
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class Consumer {

    public static void main(String[] args) {

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String wangjie489 = helloService.sayHello("wangjie489");
        System.out.println(wangjie489);
    }
}
