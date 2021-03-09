package com.daysurprise.study.rpc2.consumer;

import com.daysurprise.study.rpc.provider.HelloService;
import com.daysurprise.study.rpc2.framework.ProxyFactory;

/**
 * @Class: com.daysurprise.study.rpc2.consumer.Consumer
 * @Author: daysurprise
 * @Date: 2021/2/25
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class Consumer {

    public static void main(String[] args) {
        // 获取服务 调用服务
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        final String result = helloService.sayHello("wangjie489");
        System.out.println("consumer:" + result);
    }
}
