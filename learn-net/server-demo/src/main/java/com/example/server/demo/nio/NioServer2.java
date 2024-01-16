package com.example.server.demo.nio;

import com.example.server.demo.util.CodecUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author wfh
 * @create 2024/1/16 16:32
 */
public class NioServer2 {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public NioServer2() throws IOException {
        // 打开 Server Socket Channel
        serverSocketChannel = serverSocketChannel.open();
        // 配置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 绑定 Server port  重点是此处启动了服务端，并监听指定端口( 此处为 8080 )。
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        // 创建 Selector
        selector = Selector.open();
        // 注册 Server Socket Channel 到 Selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server 启动完成");

        handleKeys();
    }

    private void handleKeys() throws IOException {
        while (true) {
            // 通过 Selector 选择 Channel
            // 调用 Selector#select(long timeout) 方法，每 30 秒阻塞等待有就绪的 IO 事件。
            // 当不存在就绪的 IO 事件，直接 continue ，继续下一次阻塞等待。
            int selectNums = selector.select(30 * 1000L);
            if (selectNums == 0) {
                continue;
            }
            System.out.println("选择 Channel 数量：" + selectNums);

            // 遍历可选择的 Channel 的 SelectionKey 集合
            // 调用 Selector#selectedKeys() 方法，获得有就绪的 IO 事件( 也可以称为“选择的” ) Channel 对应的 SelectionKey 集合。

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 移除下面要处理的 SelectionKey
                // 因为 #select() 方法仅仅是简单地将就绪的 Channel 对应的 SelectionKey 放到 selected keys 集合中。
                // 因此，如果我们从 selected keys 集合中，获取到一个 key ，但是没有将它删除，那么下一次 #select 时, 这个 SelectionKey 还在 selectedKeys 中.
                iterator.remove();
                // 忽略无效的 SelectionKey
                if (!key.isValid()) {
                    continue;
                }

                handleKey(key);
            }

        }
    }

    private void handleKey(SelectionKey key) throws IOException {
        // 接受连接就绪
        if (key.isAcceptable()) {
            handleAcceptableKey(key);
        }
        // 读就绪
        if (key.isReadable()) {
            handleReadableKey(key);
        }
        // 写就绪
        if (key.isWritable()) {
            handleWritableKey(key);
        }
    }

    private void handleAcceptableKey(SelectionKey key) throws IOException {
        // 接受 Client Socket Channel
        // 调用 ServerSocketChannel#accept() 方法，获得连接的客户端的 SocketChannel 。
        SocketChannel clientSocketChannel = ((ServerSocketChannel) key.channel()).accept();
        // 配置为非阻塞
        // 配置客户端的 SocketChannel 为非阻塞，否则无法使用 Selector 。
        clientSocketChannel.configureBlocking(false);
        // log
        System.out.println("接受新的 Channel");
        // 注册 Client Socket Channel 到 Selector
        // 因为这个时候，服务端一般不会主动向客户端发送消息，所以不需要对 SelectionKey.OP_WRITE 事件感兴趣。
        clientSocketChannel.register(selector, SelectionKey.OP_READ, new ArrayList<String>());
    }

    private void handleReadableKey(SelectionKey key) throws IOException {
        // Client Socket Channel
        // 调用 SelectionKey#channel() 方法，获得该 SelectionKey 对应的 SocketChannel ，即客户端的 SocketChannel 。
        SocketChannel clientSocketChannel = (SocketChannel) key.channel();
        // 读取数据
        ByteBuffer readBuffer = CodecUtil.read(clientSocketChannel);
        // 处理连接已经断开的情况
        if (readBuffer == null) {
            System.out.println("断开 Channel");
            clientSocketChannel.register(selector, 0);
            return;
        }
        // 打印数据
        // 通过调用 ByteBuffer#position() 大于 0 ，来判断实际读取到数据。
        if (readBuffer.position() > 0) {
            String content = CodecUtil.newString(readBuffer);
            System.out.println("读取数据：" + content);

            // 添加到响应队列
            // 通过调用 SelectionKey#attachment() 方法，获得我们附加在 SelectionKey 的响应队列( responseQueue )。
            // 为什么不调用 SocketChannel#write(ByteBuf) 方法，直接写数据给客户端呢？虽然大多数情况下，
            // SocketChannel 都是可写的，但是如果写入比较频繁，超过 SocketChannel 的缓存区大小，就会导致数据“丢失”，并未写给客户端。
            List<String> responseQueue = (ArrayList<String>) key.attachment();
            responseQueue.add("响应：" + content);
            // 注册 Client Socket Channel 到 Selector
            // 注册客户端的 SocketChannel 到 selector 中，并对 SelectionKey.OP_WRITE 事件感兴趣。这样子，
            // 在 SocketChannel 写就绪时，在 #handleWritableKey(SelectionKey key) 方法中，统一处理写数据给客户端。
            clientSocketChannel.register(selector, SelectionKey.OP_WRITE, key.attachment());
        }
    }

    @SuppressWarnings("Duplicates")
    private void handleWritableKey(SelectionKey key) throws ClosedChannelException {
        // Client Socket Channel
        SocketChannel clientSocketChannel = (SocketChannel) key.channel();

        // 遍历响应队列
        List<String> responseQueue = (ArrayList<String>) key.attachment();
        for (String content : responseQueue) {
            // 打印数据
            System.out.println("写入数据：" + content);
            // 返回
            CodecUtil.write(clientSocketChannel, content);
        }
        responseQueue.clear();
        // 注册 Client Socket Channel 到 Selector
        clientSocketChannel.register(selector, SelectionKey.OP_READ, responseQueue);
    }


    public static void main(String[] args) throws IOException {
        NioServer2 nioServer2 = new NioServer2();
    }

}
