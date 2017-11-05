package com.wiceflow.Proxy.dynamicAgen;

import java.lang.reflect.*;

/**
 * Created by BF on 2017/10/14.
 */
public class HAHAHandler implements java.lang.reflect.InvocationHandler {

    private Object target;

    public HAHAHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        System.out.println("前");
        method.invoke(target);
        System.out.println("后");
        return null;
    }
}
