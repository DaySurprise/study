package com.daysurprise.study.rpc.framework.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Class: com.daysurprise.study.rpc.framework.register.LocalRegister
 * @Author: daysurprise
 * @Date: 2021/1/28
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class LocalRegister {

    private static Map<String, Class> REGISTER = new ConcurrentHashMap<>();

    public static void regist(String interfaceName,Class clazz){
        REGISTER.put(interfaceName,clazz);
    }

    public static Class get(String interfaceName){
        return REGISTER.get(interfaceName);
    }
}
