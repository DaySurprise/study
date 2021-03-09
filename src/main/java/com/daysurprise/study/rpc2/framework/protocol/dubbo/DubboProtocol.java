package com.daysurprise.study.rpc2.framework.protocol.dubbo;

import com.daysurprise.study.rpc2.framework.Invocation;
import com.daysurprise.study.rpc2.framework.Protocol;
import com.daysurprise.study.rpc2.framework.URL;

/**
 * @Class: com.daysurprise.study.rpc.framework.protocol.dubbo.DubboProtocol
 * @Author: daysurprise
 * @Date: 2021/1/28
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class DubboProtocol implements Protocol {
    @Override
    public void start(URL url) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getHostname(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        NettyClient nettyClient = new NettyClient();
        return nettyClient.send(url.getHostname(),url.getPort(),invocation);
    }
}
