package com.wiceflow.Thread.syn;

/**
 * 单例设计模式:确保一个类只有一个对象
 * Created by BF on 2017/9/12.
 */
public class SynDrome02 {
    public static void main(String[] args) {
        JvmThread thread1 = new JvmThread(100);
        JvmThread thread2 = new JvmThread(500);
        thread1.start();
        thread2.start();


        Jvm jvm = Jvm.getInstance(1);
        Jvm jvm1 = Jvm.getInstance1(2);
    }
}
class JvmThread extends Thread{
    private long time;
    public JvmThread() {
    }
    public JvmThread(long time) {
        this.time =time;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"-->创建:"+Jvm.getInstance(time));
    }
}


/**
 * 单例设计模式
 * 确保一个类只有一个对象
 * 懒汉式  double checking
 * 1、构造器私有化,避免外部直接创建对象
 * 2、声明一个私有的静态变量
 * 3、创建一个对外的公共的静态方法 访问该变量，如果变量没有对象，创建该对象
 */
class Jvm {
    //声明一个私有的静态变量
    private static Jvm instance =null;
    //构造器私有化,避免外部直接创建对象
    private Jvm(){

    }
    //创建一个对外的公共的静态方法 访问该变量，如果变量没有对象，创建该对象
    public static Jvm getInstance(long time){
        // c d e  -->效率  提供 已经存在对象的访问效率
        if(null==instance){
            // a b
            synchronized(Jvm.class){
                if(null==instance ){
                    try {
                        Thread.sleep(time); //延时 ，放大错误
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance =new Jvm();
                }
            }
        }//a
        return instance;
    }


    public static Jvm getInstance3(long time){
        //a b c d e  -->效率不高 c  存在对象也需要等待
        synchronized(Jvm.class){
            if(null==instance ){
                try {
                    Thread.sleep(time); //延时 ，放大错误
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance =new Jvm();
            }
            return instance;
        }
    }


    public static synchronized Jvm getInstance2(long time){
        if(null==instance ){
            try {
                Thread.sleep(time); //延时 ，放大错误
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance =new Jvm();
        }
        return instance;
    }



    public static Jvm getInstance1(long time){
        if(null==instance ){
            try {
                Thread.sleep(time); //延时 ，放大错误
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance =new Jvm();
        }
        return instance;
    }
}