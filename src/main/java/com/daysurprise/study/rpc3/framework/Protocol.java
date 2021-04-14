package com.daysurprise.study.rpc3.framework;

import com.daysurprise.study.rpc2.framework.URL;

/**
 * @Class: com.daysurprise.study.rpc3.framework.Protocol
 * @Author: daysurprise
 * @Date: 2021/3/12
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public interface Protocol {

    public String send(URL url, Invocation invocation);

    public void start(URL url);
}
