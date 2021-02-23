package com.daysurprise.study.rpc.framework.protocol.http;

import com.daysurprise.study.rpc.framework.Invocation;
import com.daysurprise.study.rpc.framework.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Class: com.daysurprise.study.rpc.framework.protocol.http.HttpServerHandler
 * @Author: daysurprise
 * @Date: 2021/1/28
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: 处理http 请求
 */
public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp) {

        try {
            //Invocation invocation = JSONObject.parseObject(req.getInputStream(), Invocation.class);

            ObjectInputStream objectInputStream = new ObjectInputStream(req.getInputStream());
            Invocation invocation = (Invocation) objectInputStream.readObject();

            String interfaceName = invocation.getInterfaceName();
            // 从本地提供者类注册中心获取对应的提供者实现类
            Class implClass = LocalRegister.get(interfaceName);
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            String result = (String) method.invoke(implClass.newInstance(), invocation.getParams());
            System.out.println("tomcat:" + result);
            IOUtils.write(result,resp.getOutputStream());
        } catch (IOException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
