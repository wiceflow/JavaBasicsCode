package com.wiceflow.Proxy.dynamicAgen;


/**
 * 测试入口
 * Created by BF on 2017/10/12.
 */
public class Client {
    public static void main(String[] args) {
        Chicken c = new Chicken();
        InvocationHandler h = new TimeHandler(c);
        java.lang.reflect.InvocationHandler b = new HAHAHandler(c);
        //MoveAble m = (MoveAble)Proxy.newProxyInstance(MoveAble.class,h);


        MoveAble n = (MoveAble) java.lang.reflect.Proxy.newProxyInstance(Chicken.class.getClassLoader(),Chicken.class.getInterfaces(),b);
        //m.move();
        //m.say();
        n.move();
    }
}
