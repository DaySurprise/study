package com.daysurprise.study.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/***
 * BIO 同步阻塞IO 服务端能接到收客户端请求才往下走 而且只能一个个处理客户端请求
 */
public class SocketServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9000);
        while (true){
            System.out.println("等待连接...");
            Socket client = serverSocket.accept();
            System.out.println("有客户端连接了");
            // 如果执行的操作比较耗时 也可以新启一个线程去完成这个事情 但是如果10万个客户端呢？
            /*new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        handle(client);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();*/
            handle(client);
        }

    }

    private static void handle(Socket client) throws IOException {
        byte[] bytes = new byte[1024];
        System.out.println("开始读取数据...");
        int read = client.getInputStream().read(bytes);
        if (read != -1){
            System.out.println("接受到客户端的信息:" + new String(bytes,0,read));
        }
        client.getOutputStream().write("Hello client".getBytes());
        client.getOutputStream().flush();
    }
}
