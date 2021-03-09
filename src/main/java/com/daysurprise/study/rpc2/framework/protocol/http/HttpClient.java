package com.daysurprise.study.rpc2.framework.protocol.http;

import com.alibaba.fastjson.JSONObject;
import com.daysurprise.study.rpc2.framework.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Class: com.daysurprise.study.rpc2.framework.protocol.http.HttpClient
 * @Author: daysurprise
 * @Date: 2021/3/2
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class HttpClient {

    public String send(String host, Integer port, Invocation invocation){
        try {
            URL url = new URL("http",host,port,"/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            OutputStream outputStream = urlConnection.getOutputStream();
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//            objectOutputStream.writeObject(invocation);
//            objectOutputStream.flush();
//            objectOutputStream.close();
            outputStream.write(JSONObject.toJSONString(invocation).getBytes());
            outputStream.flush();
            outputStream.close();
            InputStream inputStream = urlConnection.getInputStream();
            String result = IOUtils.toString(inputStream);
            return result;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
