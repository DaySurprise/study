package com.daysurprise.study.rpc.framework.register;

import com.alibaba.fastjson.JSONObject;
import com.daysurprise.study.rpc.framework.URL;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Class: com.daysurprise.study.rpc.framework.register.ZookeeperRegister
 * @Author: daysurprise
 * @Date: 2021/2/1
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: zk作为dubbo的注册中心
 */
public class ZookeeperRegister {

    static CuratorFramework client;

    static {
        client = CuratorFrameworkFactory.newClient("localhost:2181", new RetryNTimes(3, 1000));
        client.start();
    }

    private static Map<String, List<URL>> REGISTER = new ConcurrentHashMap<>();

    public static void regist(String interfaceName, URL url){
        try {
            String result = client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(String.format("/dubbo/service/%s/%s", interfaceName, JSONObject.toJSONString(url)), null);
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<URL> get(String interfaceName){
        List<URL> urlList = new ArrayList<>();
        try {
            List<String> results = client.getChildren().forPath(String.format("/dubbo/service/%s", interfaceName));
            results.forEach(result -> {
                urlList.add(JSONObject.parseObject(result,URL.class));
            });
            REGISTER.put(interfaceName,urlList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return urlList;

    }


}
