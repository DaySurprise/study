package com.daysurprise.study.rpc2.framework.protocol.http;

import com.daysurprise.study.rpc2.framework.Invocation;
import com.daysurprise.study.rpc2.framework.Protocol;
import com.daysurprise.study.rpc2.framework.URL;

/**
 * @Class: com.daysurprise.study.rpc2.framework.protocol.http.HttpProtocol
 * @Author: daysurprise
 * @Date: 2021/3/2
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class HttpProtocol implements Protocol {
    @Override
    public void start(URL url) {
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(),url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        HttpClient client = new HttpClient();
        return client.send(url.getHostname(),url.getPort(),invocation);
    }
}
