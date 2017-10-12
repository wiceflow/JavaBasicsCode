package com.wiceflow.Socket.tcp.ThreadClient;

import java.io.*;
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


    public Send() {
        console = new BufferedReader(new InputStreamReader(System.in));
    }
    public Send(Socket client) {
        this();
        try {
            dos = new DataOutputStream(client.getOutputStream());
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
    public void send() throws IOException {
        String msg = getMsgFromConsole();
        // String msg = new BufferedReader(new InputStreamReader(System.in)).readLine();
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
                send();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
