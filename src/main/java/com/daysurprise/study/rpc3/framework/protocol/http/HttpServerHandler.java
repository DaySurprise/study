package com.daysurprise.study.rpc3.framework.protocol.http;

import com.alibaba.fastjson.JSONObject;
import com.daysurprise.study.rpc3.framework.Invocation;
import com.daysurprise.study.rpc3.framework.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Class: com.daysurprise.study.rpc3.framework.protocol.http.HttpServerHandler
 * @Author: daysurprise
 * @Date: 2021/3/12
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class HttpServerHandler {
    /**
     * 处理http请求
     * @param req
     * @param resp
     */
    public void service(HttpServletRequest req, HttpServletResponse resp){
        try{
            Invocation invocation = JSONObject.parseObject(req.getInputStream(), Invocation.class);
            String interfaceName = invocation.getInterfaceName();
            Class interfaceClass = LocalRegister.get(interfaceName);
            Method method = interfaceClass.getMethod(invocation.getMethodName(),invocation.getParamTypes());
            String result = (String) method.invoke(interfaceClass.newInstance(), invocation.getParams());
            System.out.println("http -> result:" + result);
            IOUtils.write(result, resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
