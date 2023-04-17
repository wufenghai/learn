package com.example.client.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author wfh
 * @create 2023/4/17 19:38
 */
public class NioClient {


    public static void main(String[] args) throws IOException {
        // 得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        // 设置为非阻塞
        socketChannel.configureBlocking(false);
        //设置服务器端ip和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        if (!socketChannel.connect(inetSocketAddress)) {

            while (!socketChannel.finishConnect()) {
                //如果没有连接成功,客户端是非阻塞的,可以做其它工作
                System.out.println("等待连接...");
            }
        }

        // 如果连接成功,就发送数据
        String str = "hello world";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        // 发送数据 , 将buffer中的数据写入到channel中
        socketChannel.write(buffer);
        System.in.read();

    }


}
