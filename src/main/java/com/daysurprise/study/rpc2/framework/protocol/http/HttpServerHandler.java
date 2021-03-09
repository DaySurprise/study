package com.daysurprise.study.rpc2.framework.protocol.http;

import com.alibaba.fastjson.JSONObject;
import com.daysurprise.study.rpc2.framework.Invocation;
import com.daysurprise.study.rpc2.framework.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Class: com.daysurprise.study.rpc2.framework.protocol.HttpHandler
 * @Author: daysurprise
 * @Date: 2021/3/2
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class HttpServerHandler {

    /***
     * 处理http请求
     * @param req
     * @param resp
     */
    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Invocation invocation = JSONObject.parseObject(req.getInputStream(),Invocation.class);
//            ObjectInputStream objectInputStream = new ObjectInputStream(req.getInputStream());
//            com.daysurprise.study.rpc.framework.Invocation invocation = (com.daysurprise.study.rpc.framework.Invocation) objectInputStream.readObject();
            String interfaceName = invocation.getInterfaceName();
            Class interfaceClass = LocalRegister.get(interfaceName);
            Method method = interfaceClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            String result = (String) method.invoke(interfaceClass.newInstance(), invocation.getParams());
            System.out.println("tomcat:" + result);
            IOUtils.write(result,resp.getOutputStream());
        }catch (IOException | NoSuchMethodException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
