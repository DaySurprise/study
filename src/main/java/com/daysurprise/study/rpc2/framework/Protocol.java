package com.daysurprise.study.rpc2.framework;

/**
 * @Class: com.daysurprise.study.rpc2.framework.Protocol
 * @Author: daysurprise
 * @Date: 2021/3/2
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public interface Protocol {
    void start(URL url);

    String send(URL url,Invocation invocation);
}
