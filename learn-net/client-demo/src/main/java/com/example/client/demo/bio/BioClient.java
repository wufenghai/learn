package com.example.client.demo.bio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 同步阻塞io
 *
 * @author wfh
 * @create 2023/4/17 19:05
 */
public class BioClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        //向服务端写数据
        out.write("Hello server，i am client !n");
        out.flush();
        out.close();

    }

}
