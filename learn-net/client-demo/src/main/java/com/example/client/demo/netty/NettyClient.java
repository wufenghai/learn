package com.example.client.demo.netty;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;


/**
 * @author wfh
 * @create 2023/4/17 20:18
 */


public class NettyClient {

    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        nettyClient.connect("127.0.0.1", 5918);
    }

    public void connect(String host,int port) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();

            //向服务端写数据
            ByteBuf buffer = channel.alloc().buffer();
            buffer.writeBytes("hello nettyserver".getBytes(StandardCharsets.UTF_8));
            channel.writeAndFlush(buffer);

            //
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
