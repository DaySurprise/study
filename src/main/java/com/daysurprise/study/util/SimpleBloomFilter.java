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

        private   static   final   int    DEFAULT_SIZE  =   2   <<   24 ;
        private   static   final   int [] seeds         =   new   int [] {  7 ,  11 ,  13 ,  31 ,  37 ,  61 , };
        private  BitSet             bits          =   new BitSet(DEFAULT_SIZE);
        private  SimpleHash[]       func          =   new  SimpleHash[seeds.length];

        public  SimpleBloomFilter() {
            for  ( int  i  =   0 ; i  <  seeds.length; i ++ ) {
                func[i]  =   new  SimpleHash(DEFAULT_SIZE, seeds[i]);
            }
        }

        public   void  add(String value) {
            for  (SimpleHash f : func) {
                bits.set(f.hash(value),  true );
            }
        }

        public   boolean  contains(String value) {
            if  (value  ==   null ) {
                return   false ;
            }
            boolean  ret  =   true ;
            for  (SimpleHash f : func) {
                ret  =  ret  &&  bits.get(f.hash(value));
            }
            return  ret;
        }

        public   static   class  SimpleHash {

            private   int  cap;
            private   int  seed;

            public  SimpleHash( int  cap,  int  seed) {
                this .cap  =  cap;
                this .seed  =  seed;
            }

            public   int  hash(String value) {
                int  result  =   0 ;
                int  len  =  value.length();
                for  ( int  i  =   0 ; i  <  len; i ++ ) {
                    result  =  seed  *  result  +  value.charAt(i);
                }
                return  (cap  -   1 )  &  result;
            }

        }

    public   static   void  main(String[] args) {
        String value  =   "daysurprise@126.com" ;
        SimpleBloomFilter filter  =   new  SimpleBloomFilter();
        System.out.println(filter.contains(value));
        filter.add(value);
        System.out.println(filter.contains(value));
    }
}
