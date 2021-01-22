package com.daysurprise.study.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * 自定义handler需要继承netty规定好的某个HanderAdapter(规范)
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {


    private static DefaultChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(simpleDateFormat.format(new Date()) + ":[客户端]" + channel.remoteAddress() + "上线了");
        channelGroup.add(channel);
        System.out.println(channel.remoteAddress() + "上线了!");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(simpleDateFormat.format(new Date()) + ":[客户端]" + channel.remoteAddress() + "下线了");
        System.out.println(channel.remoteAddress() + "下线了!");
        System.out.println("group大小:" + channelGroup.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.forEach(ch ->{
            if (channel != ch){
                ch.writeAndFlush(simpleDateFormat.format(new Date()) + ":[客户端]" + channel.remoteAddress() + " 发送了消息:" + msg);
            }else{
                ch.writeAndFlush(simpleDateFormat.format(new Date()) + ":[自己]" + " 发送了消息:" + msg);
            }
        });

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
