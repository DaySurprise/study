package com.daysurprise.study.rpc3.framework;

import java.io.Serializable;

/**
 * @Class: com.daysurprise.study.rpc3.framework.Invocation
 * @Author: daysurprise
 * @Date: 2021/3/12
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class Invocation implements Serializable {
    private String interfaceName;
    private String methodName;
    private Class[] paramTypes;
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
