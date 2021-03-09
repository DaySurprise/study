package com.daysurprise.study.util;

import java.util.BitSet;

/**
 * @Class: com.daysurprise.study.util.SimpleBloomFilter
 * @Author: daysurprise
 * @Date: 2021/2/23
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: 简单的布隆过滤器
 */
public class SimpleBloomFilter {
    // 设置布隆过滤器的大小
    private static final int DEFAULT_SIZE = 2 << 24;
    // 产生随机数的种子，可产生6个不同的随机数产生器
    private static final int[] SEEDS = new int[] {7, 11, 13, 31, 37, 61};
    // Java中的按位存储的思想，其算法的具体实现（布隆过滤器）
    private final BitSet bits = new BitSet(DEFAULT_SIZE);
    // 根据随机数的种子，创建6个哈希函数
    private SimpleHash[] func = new SimpleHash[SEEDS.length];

    /***
     * 设置布隆过滤器所对应k（6）个哈希函数
     */
    public SimpleBloomFilter() {
        for (int i = 0; i < SEEDS.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    public void add(String value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    /***
     * 判断是否包含此值
     * @param value
     * @return
     */
    public boolean contains(String value) {
        if (value == null) {
            return false;
        }
        boolean ret = true;
        // 根据此值得到在布隆过滤器中的对应位，并判断其标志位（6个不同的哈希函数产生6种不同的映射）
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    public static class SimpleHash {

        private int cap;
        private int seed;

        /***
         * 默认构造器，哈希表长默认为DEFAULT_SIZE大小，此哈希函数的种子为seed
         */
        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(String value) {
            int result = 0;
            int len = value.length();
            for (int i = 0; i < len; i++) {
                result = seed * result + value.charAt(i);
            }
            // 产生单个信息指纹
            return (cap - 1) & result;
        }

    }

    public static void main(String[] args) {
        String value = "daysurprise@126.com";
        SimpleBloomFilter filter = new SimpleBloomFilter();
        System.out.println(filter.contains(value));
        filter.add(value);
        System.out.println(filter.contains(value));
    }
}
