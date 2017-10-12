package com.wiceflow.Socket.tcp.ThreadClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器
 * Created by BF on 2017/9/26.
 */
public class Server {
    public static void main(String[] args) {
        try {
            // 建立服务器连接
            ServerSocket server = new ServerSocket(9888);
            Socket client = server.accept();

            new Thread(new Send(client)).start();
            new Thread(new Receive(client)).start();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
