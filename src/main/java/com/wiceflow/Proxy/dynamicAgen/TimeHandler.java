package com.wiceflow.Proxy.dynamicAgen;

import java.lang.reflect.Method;

/**
 * 动态代理
 * 测试方法的运行时间
 * Created by BF on 2017/10/12.
 */
public class TimeHandler implements InvocationHandler{
    private Object target;



    public TimeHandler(Object target) {
        super();
        this.target = target;
    }



    @Override
    public void invoke(Object o, Method m) {
        long start = System.currentTimeMillis();
        System.out.println("starttime:" + start);
        try {
            // m是方法 target是目标对象
            m.invoke(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("time:" + (end-start));
    }
}
