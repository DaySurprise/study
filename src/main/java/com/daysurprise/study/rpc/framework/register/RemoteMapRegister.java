package com.daysurprise.study.rpc.framework.register;

import com.daysurprise.study.rpc.framework.URL;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class: com.daysurprise.study.rpc.framework.register.RemoteMapRegister
 * @Author: daysurprise
 * @Date: 2021/1/28
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: 远程注册接口的地址集合
 */
public class RemoteMapRegister {

    private static Map<String, List<URL>> regist = new HashMap<>();

    public static void regist(String interfaceName,URL url){
        List<URL> urls = regist.get(interfaceName);
        if (urls == null){
            urls = new ArrayList<>();
        }
        urls.add(url);
        regist.put(interfaceName,urls);
        saveFile();
    }

    public static List<URL> get(String interfaceName){
        regist = getFile();
        return regist.get(interfaceName);
    }

    private static void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/temp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(regist);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile() {
        try{
            FileInputStream fileInputStream = new FileInputStream("/temp.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
