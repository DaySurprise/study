package com.daysurprise.study.rpc3.framework;

import com.daysurprise.study.rpc2.framework.URL;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Class: com.daysurprise.study.rpc2.framework.register.RemoteRegister
 * @Author: daysurprise
 * @Date: 2021/3/2
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class RemoteRegister {
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
