package com.wiceflow.Thread.start;

/**
 * Created by BF on 2017/9/12.
 * Join 将别的线程加入，并停止当前线程等待其完成 --> 合并线程
 * eg： 在A线程中 join B线程 则停止运行A线程,等待B线程的完成再运行A线程
 */
public class JoinDemo extends Thread {

    public static void main(String[] args) throws InterruptedException {
        JoinDemo j = new JoinDemo();
        Thread t = new Thread(j); // 新生
        t.start(); // 就绪
        // 等待CPU调度

        for (int i=0;i<1000;i++){
            if (i==50){
                t.join();
            }
            System.out.println("main...." + i);
        }

    }

    @Override
    public void run() {
        for (int i=0;i<1000;i++){
            System.out.println("join...." + i);
        }
    }
}
