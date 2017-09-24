package com.wiceflow.Thread.info;

/**
 * Created by BF on 2017/9/12.
 * 自定义线程体
 */
public class MyThread implements Runnable {
    private boolean flag =true;
    private int num =0;
    @Override
    public void run() {
        while(flag){
            System.out.println(Thread.currentThread().getName()+"-->"+num++);
        }
    }
    public void stop(){
        this.flag=!this.flag;
    }
}
