package com.daysurprise.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/***
 * Linux内核函数
 * epoll_create:创建epoll实例,返回一个非负数的文件描述符,epoll相当于一个事件驱动器 是linux内核最好的多路复用IO的方法
 * epoll_wait:等待epoll实例上的事件
 * epoll_ctl:进行事件绑定
 *
 * epoll是做什么的?
 * epoll是一种事件通知的方式，每当有IO事件就绪,系统中的回调函数就会被调用,事件复杂度O(1)
 */
public class NioSelectorServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(9000));
        // 是否设置同步
        serverSocket.configureBlocking(false);

        // 打开selector处理channel 即打开epoll
        Selector selector = Selector.open();
        // 监听客户端的accept连接请求
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务启动成功...");

        while (true){
            // 阻塞等待需要处理的事件
            selector.select();
            // 获取selector中所有被注册的selectionKey事件
            Set<SelectionKey> keys = selector.keys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = server.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    System.out.println("客户端连接成功");
                }else if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                    int len = socketChannel.read(byteBuffer);
                    if (len > 0){
                        System.out.println("接收到的消息:" + new String(byteBuffer.array()));
                    }else if(len == -1){
                        System.out.println("客户端断开连接");
                        socketChannel.close();
                    }
                }
                // 从事件集合中删除这次的key 防止重复处理
                iterator.remove();
            }
        }
    }
}
