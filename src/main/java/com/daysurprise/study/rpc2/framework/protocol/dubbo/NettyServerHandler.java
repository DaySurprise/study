package com.daysurprise.study.rpc2.framework.protocol.dubbo;

import com.daysurprise.study.rpc2.framework.Invocation;
import com.daysurprise.study.rpc2.framework.register.LocalRegister;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

/**
 * @Class: com.daysurprise.study.rpc.framework.protocol.dubbo.NettyServerHandler
 * @Author: daysurprise
 * @Date: 2021/1/28
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc:
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Invocation invocation = (Invocation) msg;
        Class implClass = LocalRegister.get(invocation.getInterfaceName());
        Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
        String result = (String) method.invoke(implClass.newInstance(), invocation.getParams());
        System.out.println("netty--->" + result);
        ctx.writeAndFlush(result);
    }
}
