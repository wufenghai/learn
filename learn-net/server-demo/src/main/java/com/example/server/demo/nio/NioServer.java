package com.example.server.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * I/O多路复用模型
 * 三大核心组件：Buffer(缓冲区)、Channel(通道)、Selector(选择器/多路复用器)
 *
 * @author wfh
 * @create 2023/4/17 19:10
 */
public class NioServer {


    public static void main(String[] args) throws IOException {
        // 创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //创建一个Selector对象,
        Selector selector = Selector.open();

        // 绑定端口6666, 在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        // 把serverSocketChannel注册到selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 循环等待用户连接
        while (true) {
            //等待(阻塞)一秒, 没有事件发生
            if (selector.select(1000) == 0) {
//            if (selector.selectNow() == 0){ // 也可以设置成非阻塞的
                System.out.println("服务器等待了一秒,无连接");
                continue;
            }

            // 如果返回的>0 , 就获取相关的selectionKey集合
            // 返回关注事件的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            // 遍历selectionKeys
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()) {
                // 获取到selectionKey
                SelectionKey key = keyIterator.next();
                //根据key对应的通道获取事件并做相应处理
                if (key.isAcceptable()) {
                    //如果是OP_ACCEPT, 表示有新的客户端产生
                    //给该客户端生成SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //将socketChannnel设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到selector上, 设置事件为OP_READ,同时给socketChannel关联一个buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if (key.isReadable()) {
                    // 发生了OP_READ
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("from 客户端" + new String(buffer.array()));
                }

                // 手动从集合中移除当前的selectionKey, 防止多线程情况下的重复操作
                keyIterator.remove();

            }


        }

    }


}
