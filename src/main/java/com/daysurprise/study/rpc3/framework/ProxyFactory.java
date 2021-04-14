package com.daysurprise.study.rpc3.framework;

import com.daysurprise.study.rpc2.framework.URL;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @Class: com.daysurprise.study.rpc3.framework.ProxyFactory
 * @Author: daysurprise
 * @Date: 2021/3/12
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: 提供者类的代理工厂
 */
public class ProxyFactory {
    /**
     * 获取代理对象
     * @param clazz
     * @param <T>
     * @return
     */
    public static<T> T getProxy(final Class clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation = new Invocation(clazz.getName(), method.getName(), method.getParameterTypes(), args);
                Protocol protocol = ProtocolFactory.getProtocol();
                List<URL> urls = RemoteRegister.get(clazz.getName());
                URL url = LoadBalance.random(urls);
                String result = protocol.send(url, invocation);
                return result;
            }
        });
    }
 }
