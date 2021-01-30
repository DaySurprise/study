package com.daysurprise.study.rpc.provider;

/**
 * @Class: com.daysurprise.study.rpc.provider.HelloServiceImpl
 * @Author: daysurprise
 * @Date: 2021/1/28
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class HelloServiceImpl implements HelloService{
    @Override
    public String sayHello(String name) {
        return "Hello~ " + name;
    }
}
