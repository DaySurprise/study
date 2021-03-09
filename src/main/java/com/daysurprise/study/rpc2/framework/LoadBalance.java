package com.daysurprise.study.rpc2.framework;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;

/**
 * @Class: com.daysurprise.study.rpc2.framework.LoadBalance
 * @Author: daysurprise
 * @Date: 2021/3/2
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class LoadBalance {
    /***
     * 随机策略
     * @param urlList
     * @return
     */
    public static URL random(List<URL> urlList) {
        if (CollectionUtils.isEmpty(urlList)) {
            return null;
        }
        if (urlList.size() > 1) {
            int i = new Random().nextInt(urlList.size());
            return urlList.get(i);
        }
        return urlList.get(0);
    }
}
