package com.wiceflow.Thread.info;

/**
 * Created by BF on 2017/9/12.
 * Thread.currentThread()	 :当前线程
 * setName():设置名称
 * getName():获取名称
 * isAlive():判断状态
 */
public class InfoDemo01 {

    public static void main(String[] args) throws InterruptedException {
        MyThread it = new MyThread();
        Thread proxy = new Thread(it, "挨踢");
        proxy.setName("test");
        System.out.println(proxy.getName());
        //main
        System.out.println(Thread.currentThread().getName());

        proxy.start();
        System.out.println("启动后的状态:" + proxy.isAlive());
        Thread.sleep(200);
        it.stop();
        Thread.sleep(100);
        System.out.println("停止后的状态:" + proxy.isAlive());
    }

}
