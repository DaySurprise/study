package com.daysurprise.study.rpc2.provider;

import com.daysurprise.study.rpc.provider.HelloService;
import com.daysurprise.study.rpc.provider.HelloServiceImpl;
import com.daysurprise.study.rpc2.framework.Protocol;
import com.daysurprise.study.rpc2.framework.ProtocolFactory;
import com.daysurprise.study.rpc2.framework.URL;
import com.daysurprise.study.rpc2.framework.register.LocalRegister;
import com.daysurprise.study.rpc2.framework.register.RemoteRegister;

/**
 * @Class: com.daysurprise.study.rpc2.provider.Provider
 * @Author: daysurprise
 * @Date: 2021/2/26
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class Provider {
    public static void main(String[] args) {
        //本地注册
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);
        URL url = new URL("localhost",8080);
        //注册中心注册
        RemoteRegister.regist(HelloService.class.getName(),url);
        // 启动容器
        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);
    }

}
