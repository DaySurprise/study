package com.daysurprise.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NioSocketServer {

    // 用于保存客户端连接
    static List<SocketChannel> channelList  = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(9000));
        // 是否设置同步
        serverSocket.configureBlocking(false);
        System.out.println("服务启动成功...");
        while (true){
            SocketChannel channel = serverSocket.accept();
            if (channel != null){
                System.out.println("客户端连接成功...");
                // 设置 socketChannel 为非阻塞
                channel.configureBlocking(false);
                channelList.add(channel);
            }
            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()){
                SocketChannel socketChannel = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                int read = socketChannel.read(byteBuffer);
                // 如果读取有数据 就把数据打印出来
                if (read >0){
                    System.out.println(new String(byteBuffer.array()));
                }
                // 如果客户端断开 将channel从list中拿掉
                else if(read == -1){
                    iterator.remove();
                    System.out.println("客户端断开连接...");
                }

            }

        }


    }
}
