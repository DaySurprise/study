package com.daysurprise.study.rpc.framework;

/**
 * @Class: com.daysurprise.study.rpc.framework.Protocol
 * @Author: daysurprise
 * @Date: 2021/1/28
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public interface Protocol {

    void start(String hostname, Integer port);

    String send(URL url,Invocation invocation);
}
