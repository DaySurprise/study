package com.daysurprise.study.guava.interfaceIntercept;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Class: com.daysurprise.study.guava.interfaceIntercept.Counter
 * @Author: daysurprise
 * @Date: 2021/1/29
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class Counter {
    static LoadingCache<Long, AtomicLong> count = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS).build(new CacheLoader<Long, AtomicLong>() {
        @Override
        public AtomicLong load(Long aLong) throws Exception {
            System.out.println("load call");
            return new AtomicLong(0L);
        }
    });
    static long limits = 10;
    static int counter = 0;
    public static synchronized int getCounter() throws ExecutionException {
        while (true){
            Long currentSecond = System.currentTimeMillis() / 1000;
            if (count.get(currentSecond).getAndIncrement() > limits){
                continue;
            }
            return counter++;
        }

    }
}
