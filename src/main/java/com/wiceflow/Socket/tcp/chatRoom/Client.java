package com.wiceflow.Socket.tcp.chatRoom;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * 创建客户端  发送数据+接收数据
 * 写出数据 : 输出流
 * 写入数据 : 输入流
 * Created by BF on 2017/9/26.
 */
public class Client {
    private static String name = null;

    public static void main(String[] args) throws IOException {
        System.out.println("请输入你的名字");
        Scanner sc = new Scanner(System.in);
        name = sc.nextLine();
        if (name != null && !name.equals("")) {
            // 建立客户端连接
            Socket client = new Socket("localhost", 9888);
            new Thread(new Send(client, name)).start();
            new Thread(new Receive(client)).start();
        }

    }
}
