package com.wiceflow.Thread.start;

/**
 * Created by BF on 2017/9/12.
 * 暂停线程, Thread,yield 写在哪个线程里便暂停哪一个线程
 */
public class YieldDemo extends Thread {
    public static void main(String[] args) {
        YieldDemo y = new YieldDemo();
        Thread t = new Thread(y); // 新生
        t.start(); // 就绪
        // 等待CPU调度

        for (int i=0;i<1000;i++){
            if (i%20==0){
                // 暂停线程
                Thread.yield();
            }
            System.out.println("main...." + i);
        }
    }
    @Override
    public void run() {
        for (int i=0;i<1000;i++){
            System.out.println("Yield...." + i);
        }
    }
}
