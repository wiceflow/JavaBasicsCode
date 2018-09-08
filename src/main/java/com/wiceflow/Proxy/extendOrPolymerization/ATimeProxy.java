package com.wiceflow.Proxy.extendOrPolymerization;

import java.util.Random;

/**
 * 聚合-静态代理
 * Created by BF on 2017/10/12.
 */
public class ATimeProxy implements Sayable{
    // 创建代理对象
    private Sayable s;
    ATimeProxy(Sayable s) {
        this.s = s;
    }
    @Override
    public void say() throws InterruptedException {
            long start = System.currentTimeMillis();
            // 执行代理对象的方法，在其前后加入我们的逻辑即可
            s.say();
            Thread.sleep(new Random().nextInt(10000));
            long end = System.currentTimeMillis();
            System.out.println("执行时间为：" + (end-start));
    }
}
