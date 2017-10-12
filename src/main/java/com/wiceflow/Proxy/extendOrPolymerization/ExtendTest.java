package com.wiceflow.Proxy.extendOrPolymerization;

/**
 * 使用继承来代理
 * Created by BF on 2017/10/12.
 */
public class ExtendTest extends A{
    public void say() throws InterruptedException {
        long start = System.currentTimeMillis();
        super.say();
        Thread.sleep(300);
        long end = System.currentTimeMillis();
        System.out.println("执行时间为：" + (end-start));
    }
}
