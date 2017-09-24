package com.wiceflow.Thread.pro;

/**
 * 测试类
 * Created by BF on 2017/9/13.
 */
public class App {
    public static void main(String[] args) {
        //共同的资源 --> 可能会造成死锁
        Movie m = new Movie();

        //多线程
        Player p = new Player(m);
        Watcher w = new Watcher(m);

        new Thread(p).start();
        new Thread(w).start();
    }
}