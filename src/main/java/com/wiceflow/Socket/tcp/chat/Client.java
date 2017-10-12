package com.wiceflow.Socket.tcp.chat;

import com.wiceflow.Socket.tcp.ThreadClient.Send;

import java.io.*;
import java.net.Socket;

/**
 * 创建客户端  发送数据+接收数据
 * 写出数据 : 输出流
 * 写入数据 : 输入流
 * Created by BF on 2017/9/26.
 */
public class Client {
    public static void main(String[] args) {
        try {
            // 建立客户端连接
            Socket client = new Socket("localhost", 9888);
            // 控制台输入流
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            // 输入流
            DataInputStream dis = new DataInputStream(client.getInputStream());
            // 输出流
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());

            while (true) {
                String info = console.readLine();
                dos.writeUTF(info);
                dos.flush();
                String msg = dis.readUTF();
                System.out.println(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
