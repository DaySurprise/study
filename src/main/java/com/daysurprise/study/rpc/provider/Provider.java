package com.daysurprise.study.rpc.provider;

import com.daysurprise.study.rpc.framework.Protocol;
import com.daysurprise.study.rpc.framework.ProtocolFactory;
import com.daysurprise.study.rpc.framework.register.RemoteMapRegister;
import com.daysurprise.study.rpc.framework.URL;
import com.daysurprise.study.rpc.framework.register.LocalRegister;

/**
 * @Class: com.daysurprise.study.rpc.provider.Provider
 * @Author: daysurprise
 * @Date: 2021/1/28
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class Provider {

    public static void main(String[] args) {

        // 本地注册
        LocalRegister.regist(HelloService.class.getName(),HelloServiceImpl.class);

        // 远程注册中心注册
        RemoteMapRegister.regist(HelloService.class.getName(),new URL("localhost",8080));

        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start("localhost",8080);
    }
}
