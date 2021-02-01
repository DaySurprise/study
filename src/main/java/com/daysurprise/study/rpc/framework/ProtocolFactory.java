package com.daysurprise.study.rpc.framework;

import com.daysurprise.study.rpc.framework.protocol.dubbo.DubboProtocol;
import com.daysurprise.study.rpc.framework.protocol.http.HttpProtocol;
import org.springframework.util.StringUtils;

/**
 * @Class: com.daysurprise.study.rpc.framework.ProtocolFactory
 * @Author: daysurprise
 * @Date: 2021/1/28
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: 协议工厂 根据配置返回对应的协议
 */
public class ProtocolFactory {

    public static Protocol getProtocol(){
        String protocolName = System.getProperty("protocol");
        System.out.println("系统配置的协议类型:" + protocolName);
        if (StringUtils.isEmpty(protocolName)){
            return new HttpProtocol();
        }
        if ("http".equals(protocolName)){
            return new HttpProtocol();
        }else if ("dubbo".equals(protocolName)){
            return new DubboProtocol();
        }
        return new HttpProtocol();
    }
}
