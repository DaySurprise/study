package com.daysurprise.study.chat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/***
 * 自定义handler需要继承netty规定好的某个HanderAdapter(规范)
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /***
     * 读取客户端发送的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器读取线程:" + Thread.currentThread().getName());
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端发送的消息:" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 数据读取完毕的处理方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello Client", CharsetUtil.UTF_8);
        ctx.writeAndFlush(byteBuf);
    }

    /***
     * 处理异常 一般要关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
