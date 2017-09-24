package com.wiceflow.Thread.create;

/**
 * Created by BF on 2017/9/11.
 * 模拟龟兔赛跑
 * 创建多线程继承Thread类 --> 重写run()方法  线程体
 * 使用线程 创建子类对象 调用对象 .start()方法启用线程  -->父类
 */
public class Rabbit extends Thread{
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println("兔子跑了第 " + i +"步");
        }
    }
}
class Total extends Thread{
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println("乌龟跑了第 " + i +"步");
        }
    }
}
