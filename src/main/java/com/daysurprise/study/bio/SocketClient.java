package com.daysurprise.study.bio;

import java.io.IOException;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",9000);
        socket.getOutputStream().write("Hello I'm client".getBytes());
        socket.getOutputStream().flush();
        System.out.println("向服务器发送数据结束...");
        /*byte[] bytes = new byte[1024];
        socket.getInputStream().read(bytes);
        System.out.println("从服务器读取到的数据:" + new String(bytes));*/
        socket.close();
    }
}
