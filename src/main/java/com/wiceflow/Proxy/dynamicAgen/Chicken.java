package com.wiceflow.Proxy.dynamicAgen;

/**
 * 需要代理的类
 * Created by BF on 2017/10/12.
 */
public class Chicken implements MoveAble {
    @Override
    public void move(){
        System.out.println("我可以走路");
    }

    @Override
    public void say() {
        System.out.println("我不会说话");
    }
}
