package com.daysurprise.study.rpc3.framework.protocol.http;

import com.daysurprise.study.rpc2.framework.URL;
import com.daysurprise.study.rpc3.framework.Invocation;
import com.daysurprise.study.rpc3.framework.Protocol;

/**
 * @Class: com.daysurprise.study.rpc3.framework.protocol.http.HttpProtocol
 * @Author: daysurprise
 * @Date: 2021/3/12
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class HttpProtocol implements Protocol {
    @Override
    public String send(URL url, Invocation invocation) {
        HttpClient httpClient = new HttpClient();
        return httpClient.send(url.getHostname(), url.getPort(), invocation);
    }

    @Override
    public void start(URL url) {
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }
}
