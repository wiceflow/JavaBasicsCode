package com.wiceflow.Socket.tcp.ThreadClient;

import java.io.*;
import java.net.Socket;

/**
 * 创建客户端  发送数据+接收数据
 * 写出数据 : 输出流
 * 写入数据 : 输入流
 * Created by BF on 2017/9/26.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        // 建立客户端连接
        Socket client = new Socket("localhost", 9888);
        new Thread(new Send(client)).start();
        new Thread(new Receive(client)).start();

    }
}
