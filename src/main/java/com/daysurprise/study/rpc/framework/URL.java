package com.daysurprise.study.rpc.framework;

import java.io.Serializable;

/**
 * @Class: com.daysurprise.study.rpc.framework.URL
 * @Author: daysurprise
 * @Date: 2021/1/28
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: 注册地址
 */
public class URL implements Serializable {

    private String hostname;
    private Integer port;

    public URL(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
