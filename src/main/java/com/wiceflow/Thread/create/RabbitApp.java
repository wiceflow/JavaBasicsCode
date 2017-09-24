package com.wiceflow.Thread.create;

/**
 * Created by BF on 2017/9/11.
 */
public class RabbitApp {
    public static void main(String[] args) {
        // 创建子类对象
        Rabbit rabbit = new Rabbit();
        Total total = new Total();

        // 调用子类对象的start方法,不能调用run方法 如果调用了run方法那就是顺序执行
        rabbit.start();
        total.start();

        for (int i=0;i<100;i++){
            System.out.println("Main方法走了第 " + i + "步" );
        }
    }
}
