package com.daysurprise.study.rpc3.framework;

import com.daysurprise.study.rpc3.framework.protocol.http.HttpProtocol;

/**
 * @Class: com.daysurprise.study.rpc3.framework.ProtocolFactory
 * @Author: daysurprise
 * @Date: 2021/3/12
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class ProtocolFactory {
    public static Protocol getProtocol(){
        String protocolName = System.getProperty("protocol");
        System.out.println("系统配置的协议类型:" + protocolName);
        if ("http".equals(protocolName)){
            return new HttpProtocol();
        }
//        else if ("dubbo".equals(protocolName)){
//            return new DubboProtocol();
//        }
        return new HttpProtocol();
    }
}
