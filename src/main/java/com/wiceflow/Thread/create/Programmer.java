package com.wiceflow.Thread.create;
/**
 推荐  Runnable 创建线程
 1)、避免单继承的局限性
 2)、便于共享资源


 使用 Runnable 创建线程
 1、类 实现 Runnable接口 +重写 run()   -->真实角色类
 2、启动多线程  使用静态代理  --> 静态代理模式
 1)、创建真实角色
 2)、创建代理角色 +真实角色引用
 3)、调用 .start() 启动线程

 *
 *Created by BF on 2017/9/11.
 */
public class Programmer implements Runnable {

    @Override
    public void run() {
        for(int i=0;i<1000;i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("一边敲helloworld....");
        }
    }
}
