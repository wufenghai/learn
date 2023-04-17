package com.example.server.demo.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 同步阻塞io
 * 服务端会进行线程等待，进入阻塞状态，直到收到客户端的请求信息时候才会继续往下执行。
 * @author wfh
 * @create 2023/4/17 17:54
 */
public class BioServer {

    public static void main(String[] args) throws IOException {
        //服务段绑定窗口，开启监听
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true) {
            //socket代表和一个客户端的连接，accpect是阻塞
            System.out.println("已经创建服务端连接 ");
            Socket socket = serverSocket.accept();
            System.out.println("客户端数据已接受");
            //读取客户端数据
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s = bufferedReader.readLine();
            System.out.println("收到来自客户端发来的数据：" + s);

        }
    }
}
