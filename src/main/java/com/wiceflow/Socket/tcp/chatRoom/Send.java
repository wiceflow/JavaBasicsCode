package com.wiceflow.Socket.tcp.chatRoom;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 封装客户端发送流 -- 多线程
 * Created by BF on 2017/9/26.
 */
public class Send implements Runnable {
    // 控制台输入流
    private BufferedReader console;
    // 输出流
    private DataOutputStream dos;
    // 线程是否运行
    private boolean isRunning = true;
    // 客户端名称
    private String name;

    private Socket client;

    public Send() {
        console = new BufferedReader(new InputStreamReader(System.in));
    }
    public Send(Socket client,String name) {
        this();
        this.client = client;
        this.name = name;
        try {
            dos = new DataOutputStream(client.getOutputStream());
            send(this.name);
        } catch (IOException e) {
            isRunning = false;
            CloseUtil.closeAll(dos,console);
        }
    }

    /**
     * 从控制台接收数据
     * @return console.readLine(); 用户输入数据
     */
    private String getMsgFromConsole(){
        try {
            return console.readLine();
        } catch (IOException e) {
            new Exception("控制台接收数据异常");
        }
        return "";
    }

    /**
     * 从控制台接收数据
     * 发送数据
     */
    public void send(String msg) throws IOException {
        // String msg = new BufferedReader(new InputStreamReader(System.in)).readLine();
        if (msg.equals("bbb")){
            client.close();
        }
        if (msg!=null&&!msg.equals("")){
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                isRunning = false;
                CloseUtil.closeAll(dos,console);
            }
        }
    }

    @Override
    public void run() {
        // 线程体
        while (isRunning){
            try {
                send(getMsgFromConsole());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
