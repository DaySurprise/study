package com.daysurprise.study.rpc2.framework;

import java.io.Serializable;

/**
 * @Class: com.daysurprise.study.rpc2.framework.Invocation
 * @Author: daysurprise
 * @Date: 2021/2/25
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:  rpc通信需要传递的对象
 */
public class Invocation implements Serializable {
    /***
     * 接口名
     */
    private String interfaceName;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数类型
     */
    private Class[] paramTypes;
    /**
     * 参数值
     */
    private Object[] params;

    public Invocation(String interfaceName, String methodName, Class[] paramTypes, Object[] params) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
