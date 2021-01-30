package com.daysurprise.study.rpc.framework;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;

/**
 * @Class: com.daysurprise.study.rpc.framework.LoadBalance
 * @Author: daysurprise
 * @Date: 2021/1/28
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: 消费者调用哪个服务者的均衡策略
 */
public class LoadBalance {


    /***
     * 随机策略
     * @param urlList
     * @return
     */
    public static URL random(List<URL> urlList){
        if (CollectionUtils.isEmpty(urlList)){
            return null;
        }
        if (urlList.size() > 1){
            int i = new Random().nextInt(urlList.size());
            return urlList.get(i);
        }
        return urlList.get(0);
    }
}
