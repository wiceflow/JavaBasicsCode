package com.wiceflow.Proxy.extendOrPolymerization;

/**
 * 测试主入口
 * Created by BF on 2017/10/12.
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        // 1.继承
        //ExtendTest E = new ExtendTest();
        //E.say();
        // 2.聚合 静态代理
        Sayable s = new ATimeProxy(a);
        s.say();
    }
}
