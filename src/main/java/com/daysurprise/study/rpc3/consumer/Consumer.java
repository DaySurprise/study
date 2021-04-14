package com.daysurprise.study.rpc3.consumer;

import com.daysurprise.study.rpc.provider.HelloService;
import com.daysurprise.study.rpc3.framework.ProxyFactory;

/**
 * @Class: com.daysurprise.study.rpc3.consumer.Consumer
 * @Author: daysurprise
 * @Date: 2021/3/12
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class Consumer {

    public static void main(String[] args) {
//        Invocation invocation = new Invocation(HelloService.class.getName(),"sayHello",new Class[]{String.class},new Object[]{"王杰"});
//        HttpClient client = new HttpClient();
//        String result = client.send("localhost", 8081, invocation);
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("王杰");
        System.out.println("rpc调用返回结果:" + result);
    }
}
