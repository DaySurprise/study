package com.daysurprise.study.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AioServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(9000));
        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel socketChannel, Object attachment) {
                try{
                    System.out.println("2---" + Thread.currentThread().getName());
                    server.accept(attachment,this);
                    System.out.println(socketChannel.getRemoteAddress());
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    socketChannel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer byteBuffer) {
                            System.out.println("3---" + Thread.currentThread().getName());
                            byteBuffer.flip();
                            System.out.println(new String(byteBuffer.array(),0,result));
                            socketChannel.write(ByteBuffer.wrap("Hello Client".getBytes()));
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer byteBuffer) {
                            exc.printStackTrace();
                        }
                    });

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
            }
        });
        System.out.println("1---" + Thread.currentThread().getName());
        Thread.sleep(Integer.MAX_VALUE);
    }
}
