package com.wiceflow.Socket.tcp.chatRoom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务器 --> 建立一对多个客户端的服务器
 *
 * 2.修改部分代码 实现私聊
 * 3.30号更新，新的一个人加入聊天室后会提醒
 * 私有内部类方法
 * Created by BF on 2017/9/26.
 */
public class Server {
    // 每有一个新的客户端连接便加入数组中
    // static 确保只初始化一次
    private static List<MyChannel> myChannels = new ArrayList<MyChannel>();

    public static void main(String[] args) {
        new Server().start();
    }

    // main方法是静态方法 无法调用成员内部类，所以封装起来
    private void start() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(9888);
            while (true) {
                // 死循环连接客户端 --》 打开一条新的道路
                Socket client = server.accept();
                // 当有客户端连接的时候便加入到数组中
                MyChannel m = new MyChannel(client);
                myChannels.add(m); // 统一管理
                // 启动线程
                new Thread(m).start();
            }
        } catch (IOException e) {
            System.out.println("服务器创建连接失败");
        }
    }

    private class MyChannel implements Runnable {
        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean isRunning = true;
        private String name;
        private Socket client;
        public MyChannel(Socket client) {
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
                this.name = receive();
                String welcome = "欢迎 " + this.name + " 加入聊天室";
                System.out.println(welcome);
            } catch (IOException e) {
                // 如果异常 直接把两个流都关了
                CloseUtil.closeAll(dis, dos);
                isRunning = false;
            }
        }

        /**
         * 读取数据
         *
         * @return
         */
        private String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                CloseUtil.closeAll(dis, dos);
                isRunning = false;
                // 若读取数据异常说明当前客户端报错，则移除自身
                myChannels.remove(this);
            }
            return msg;
        }

        /**
         * 发送数据
         *
         * @param msg
         */
        private void send(String msg) {
            if (msg == null || msg.equals("")) {
                return;
            }
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                CloseUtil.closeAll(dis, dos);
                isRunning = false;
                // 若读取数据异常说明当前客户端报错，则移除自身
                myChannels.remove(this);
            }
        }
        // 发送给聊天室其他人  测试成功
        private void sendOther(String msg){
            // 私聊 以@开头
            if (msg.startsWith("@")&&msg.indexOf("：")>-1){
                // 获取私聊对象
                String name = msg.substring(1,msg.indexOf("："));
                // 获取私聊内容
                String context = msg.substring(msg.indexOf("：")+1);
                for (MyChannel m:myChannels){
                    if (m.name.equals(name)){
                        m.send(this.name +" 的私聊信息 :"  +context);
                    }
                }
            }else {
                // 遍历容器  所有客户端
                for (MyChannel m : myChannels) {
                    // 如果是自己就不发送给自己
                    if (m == this) {
                        continue;
                    }
                    m.send(this.name + " : " + msg);
                }
            }
        }
        // 判断客户端是否关闭
        public boolean isConnected(){
            try{
                client.sendUrgentData(0xFF);
                return true;
            }catch(Exception e){
                return false;
            }
        }


        @Override
        public void run() {
            while (isRunning) {
                sendOther(this.receive());
                boolean i = isConnected();
                if (!i){
                    System.out.println(this.name + "客户端关闭");
                }
            }
        }
    }
}
