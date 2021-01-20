package com.daysurprise.study.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

public class AioClient {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",9000)).get();
        socketChannel.write(ByteBuffer.wrap("Hello Server".getBytes()));
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        Integer len = socketChannel.read(byteBuffer).get();
        if (len != -1){
            System.out.println("客户端收到的消息:" + new String(byteBuffer.array(),0,len));
        }

    }
}
