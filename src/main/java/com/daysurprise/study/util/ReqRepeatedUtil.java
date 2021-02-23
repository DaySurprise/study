package com.daysurprise.study.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * @Class: com.daysurprise.study.util.ReqRepeatedUtil
 * @Author: daysurprise
 * @Date: 2021/2/23
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: 业务请求防重：将请求参数按键值排序并进行md5加密 如果加密后的串相同的话 表示重复请求 还能排除一些不属于业务的键值
 */
@Slf4j
public class ReqRepeatedUtil {

    /**
     *
     * @param reqJson 请求的参数，这里通常是JSON
     * @param excludeKeys 请求参数里面要去除哪些字段再求摘要
     * @return 去除参数的MD5摘要
     */
    public String dedupParamMD5(final String reqJson, String... excludeKeys) {

        TreeMap paramTreeMap = JSON.parseObject(reqJson, TreeMap.class);
        if (excludeKeys!=null) {
            List<String> dedupExcludeKeys = Arrays.asList(excludeKeys);
            if (!dedupExcludeKeys.isEmpty()) {
                for (String dedupExcludeKey : dedupExcludeKeys) {
                    paramTreeMap.remove(dedupExcludeKey);
                }
            }
        }

        String paramTreeMapJson = JSON.toJSONString(paramTreeMap);
        String md5deDupParam = md5(paramTreeMapJson);
        log.debug("md5deDupParam = {}, excludeKeys = {} {}", md5deDupParam, Arrays.deepToString(excludeKeys), paramTreeMapJson);
        return md5deDupParam;
    }

    private static String md5(String src) {
        String res = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] mdBytes = messageDigest.digest(src.getBytes());
            res = DatatypeConverter.printHexBinary(mdBytes);
        } catch (Exception e) {
            log.error("",e);
        }
        return res;
    }

    public static void main(String[] args) {
        //两个请求一样，但是请求时间差一秒
        String req = "{\n" +
                "\"requestTime\" :\"20190101120001\",\n" +
                "\"requestValue\" :\"1000\",\n" +
                "\"requestKey\" :\"key\"\n" +
                "}";

        String req2 = "{\n" +
                "\"requestTime\" :\"20190101120002\",\n" +
                "\"requestValue\" :\"1000\",\n" +
                "\"requestKey\" :\"key\"\n" +
                "}";

        //全参数比对，所以两个参数MD5不同
        String dedupMD5 = new ReqRepeatedUtil().dedupParamMD5(req);
        String dedupMD52 = new ReqRepeatedUtil().dedupParamMD5(req2);
        System.out.println("req1MD5 = "+ dedupMD5+" , req2MD5="+dedupMD52);

        //去除时间参数比对，MD5相同
        String dedupMD53 = new ReqRepeatedUtil().dedupParamMD5(req,"requestTime");
        String dedupMD54 = new ReqRepeatedUtil().dedupParamMD5(req2,"requestTime");
        System.out.println("req1MD5 = "+ dedupMD53+" , req2MD5="+dedupMD54);

    }
}
