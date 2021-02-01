package com.daysurprise.study.rpc.framework;

import com.daysurprise.study.rpc.framework.register.ZookeeperRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @Class: com.daysurprise.study.rpc.framework.ProxyFactory
 * @Author: daysurprise
 * @Date: 2021/1/28
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: 动态代理模式
 */
public class ProxyFactory<T> {

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class interfaceClass){
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation = new Invocation(interfaceClass.getName(),method.getName(), method.getParameterTypes(), args);
                List<URL> urls = ZookeeperRegister.get(interfaceClass.getName());
                URL url = LoadBalance.random(urls);
                Protocol protocol = ProtocolFactory.getProtocol();
                String result = protocol.send(url,invocation);
                return result;
            }
        });

    }
}
