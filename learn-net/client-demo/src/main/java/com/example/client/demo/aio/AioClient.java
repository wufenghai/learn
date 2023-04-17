package com.example.client.demo.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author wfh
 * @create 2023/4/17 19:55
 */
public class AioClient {
    private AsynchronousSocketChannel client;

    public AioClient(String host, int port) throws IOException, InterruptedException, ExecutionException {
        this.client = AsynchronousSocketChannel.open();
        Future<?> future = client.connect(new InetSocketAddress(host, port));
        future.get();
    }

    public void write(byte b) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);
        byteBuffer.put(b);
        byteBuffer.flip();
        client.write(byteBuffer);
    }

    public static void main(String[] args) throws Exception {
        AioClient client = new AioClient("localhost", 7788);
        client.write((byte) 123);
    }

}
