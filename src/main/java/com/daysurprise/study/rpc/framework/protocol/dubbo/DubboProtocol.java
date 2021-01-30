package com.daysurprise.study.rpc.framework.protocol.dubbo;

import com.daysurprise.study.rpc.framework.Invocation;
import com.daysurprise.study.rpc.framework.Protocol;
import com.daysurprise.study.rpc.framework.URL;

/**
 * @Class: com.daysurprise.study.rpc.framework.protocol.dubbo.DubboProtocol
 * @Author: daysurprise
 * @Date: 2021/1/28
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class DubboProtocol implements Protocol {
    @Override
    public void start(String hostname, Integer port) {

    }

    @Override
    public String send(URL url, Invocation invocation) {
        return null;
    }
}
