package com.wiceflow.Thread.pro;

/**
 * Created by BF on 2017/9/13.
 * 一个场景,共同的资源
 * 生产者消费者模式 信号灯法
 * wait() :等待，释放锁   sleep 不释放锁
 * notify()/notifyAll():唤醒
 * 与 synchronized 共同使用,不可单独使用
 */
public class Movie {
    // 测试电影传参而已
    private String pic;
    //信号灯
    //flag -->T 生产生产，消费者等待 ,生产完成后通知消费
    //flag -->F 消费者消费 生产者等待,消费完成后通知生产
    private boolean flag = true;

    /**
     * 模拟装填 --> 生产者
     *
     * @param pic 外部传参
     */
    public synchronized void play(String pic) {
        if (!flag) { //生产者等待
            try {
                this.wait();// 进程暂停,跑到别的线程去,释放了锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 开始生产
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("生产了:" + pic);
        // 生产完毕
        this.pic = pic;
        // 通知消费
        // 唤醒其他线程 然后修改标识,在唤醒其他线程的同时自己还在运行,不过标识改变,自己的便进入wait方法
        this.notify();
        // 生产者停下
        this.flag = false;
    }

    /**
     * 模拟播放 --> 消费者
     */
    public synchronized void watch() {
        if (flag) { //消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //开始消费
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("消费了" + pic);
        System.out.println();
        //消费完毕
        //通知生产
        this.notifyAll();
        //消费停止
        this.flag = true;
    }
}
