package com.wiceflow.JVM;

/**
 * Created by BF on 2017/9/14.
 * 了解JVM加载类全过程
 */
public class demo01 {
    public static void main(String[] args) throws ClassNotFoundException {
//        // 当A对象被创建的时候 先会执行静态代码块,再实执行A的构造方法
//        A a = new A();
//        System.out.println(a.width);
//        // 输出顺序为  创建初始化类A--> 创建A对象  --> width = 300
//        A a2 = new A();
//        // 只会打印 创建A对象，不会再加载第二次

        // 主动引用
//        new A();
        //System.out.println(A.width);
        //Class.forName("com.wiceflow.JVM.A");

        // 被动引用
        //System.out.println(A.MAX);
        //A[] as = new A[10];
        //System.out.println(B.width);
    }
}

class A extends A_Father{
    public static int width = 100;
    public static final int MAX = 200;
    static {
        System.out.println("静态初始化类A");
        width = 300;
    }

    public A(){
        System.out.println("创建A对象");
    }
}
class A_Father{
    static {
        System.out.println("静态初始化A的父类");
    }
}
class B extends A{
    static {
        System.out.println("静态初始化类B");
    }
}