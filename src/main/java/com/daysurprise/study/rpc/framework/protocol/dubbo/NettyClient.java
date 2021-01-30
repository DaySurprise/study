package com.daysurprise.study.rpc.framework.protocol.dubbo;

import com.daysurprise.study.rpc.framework.Invocation;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Class: com.daysurprise.study.rpc.framework.protocol.dubbo.NettyClient
 * @Author: daysurprise
 * @Date: 2021/1/28
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class NettyClient {

    public NettyClientHandler nettyClientHandler = null;

    public static ExecutorService executorService = Executors.newCachedThreadPool();

    public void start(String hostname, Integer port){
        nettyClientHandler = new NettyClientHandler();

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        bootstrap.group(eventExecutors)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                        pipeline.addLast(new ObjectEncoder());
                        pipeline.addLast(nettyClientHandler);
                    }
                });
        try {
            bootstrap.connect(hostname,port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String send(String hostname, Integer port, Invocation invocation){
        if (nettyClientHandler == null){
            start(hostname, port);
        }
        nettyClientHandler.setInvocation(invocation);

        try {
            return (String) executorService.submit(nettyClientHandler).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
