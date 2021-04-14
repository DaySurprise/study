package com.daysurprise.study.rpc3.provider;

import com.daysurprise.study.rpc.provider.HelloService;
import com.daysurprise.study.rpc.provider.HelloServiceImpl;
import com.daysurprise.study.rpc2.framework.URL;
import com.daysurprise.study.rpc3.framework.Protocol;
import com.daysurprise.study.rpc3.framework.ProtocolFactory;
import com.daysurprise.study.rpc3.framework.RemoteRegister;
import com.daysurprise.study.rpc3.framework.register.LocalRegister;

/**
 * @Class: com.daysurprise.study.rpc3.provider.Provider
 * @Author: daysurprise
 * @Date: 2021/3/12
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class Provider {
    public static void main(String[] args) {
        // 本地服务注册
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);

        URL url = new URL("localhost",8081);

        // 远程注册
        RemoteRegister.regist(HelloService.class.getName(),url);

        // 启动tomcat
        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);
    }
}
