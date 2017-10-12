package com.wiceflow.Proxy.dynamicAgen;

import java.io.File;

/**
 * 测试入口
 * Created by BF on 2017/10/12.
 */
public class Client {
    public static void main(String[] args) {
        Chicken c = new Chicken();
        InvocationHandler h = new TimeHandler(c);
        System.out.println(MoveAble.class);
        MoveAble m = (MoveAble)Proxy.newProxyInstance(MoveAble.class,h);
        m.move();
    }
}
