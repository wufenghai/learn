package com.example.server.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author wfh
 * @create 2023/4/17 20:18
 */
public class ServerInboundHandler2 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ServerInboundHandler2 channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ServerInboundHandler2 channelInactive");
        super.channelInactive(ctx);
    }

    /**
     * 有数据时的回调
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        String content = new String(bytes, Charset.defaultCharset());
        System.out.println("收到的数据:"+content);
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ServerInboundHandler2 channelReadComplete");
        //向客户端写回数据
        Channel channel = ctx.channel();
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes("hello,nettclient".getBytes(StandardCharsets.UTF_8));
        channel.writeAndFlush(buffer);
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("ServerInboundHandler2 exceptionCaught," + cause.getMessage());
        super.exceptionCaught(ctx, cause);
    }
}