package com.wiceflow.Socket.tcp.chat;

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

            // 写出数据
            // 控制台输入流
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            // 输入流
            DataInputStream dis = new DataInputStream(client.getInputStream());
            // 输出流
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());

            while (true) {
                System.out.println(dis.readUTF());
                String info = console.readLine();
                dos.writeUTF("服务器返回->" + info);
                dos.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
