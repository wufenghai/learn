package com.example.server.demo.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

/**
 * 应用层到内核去读取（read）网络传输过来的数据，然后返回，内核再通过回调函数返回数据，因操作系统而异，操作系统不一定有这个功能
 *
 * @author wfh
 * @create 2023/4/17 19:53
 */
public class AioServer {

    public static void main(String[] args) throws Exception {
        AioServer server = new AioServer(7788);

        Thread.sleep(1000000);
    }

    public AioServer(int port) throws IOException {
        final AsynchronousServerSocketChannel listener = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port));

        listener.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            public void completed(AsynchronousSocketChannel ch, Void att) {
                // 接受下一个连接
                listener.accept(null, this);

                // 处理当前连接
                handle(ch);
            }

            public void failed(Throwable exc, Void att) {

            }
        });

    }

    public void handle(AsynchronousSocketChannel ch) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);
        try {
            ch.read(byteBuffer).get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byteBuffer.flip();
        System.out.println(byteBuffer.get());
        // Do something
    }


}
