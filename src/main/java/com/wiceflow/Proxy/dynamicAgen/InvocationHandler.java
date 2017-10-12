package com.wiceflow.Proxy.dynamicAgen;

import java.lang.reflect.Method;

/**
 * 动态代理 方法接口
 * Created by BF on 2017/10/12.
 */
public interface InvocationHandler {
    //TODO 应该还有一个所传参数是方法的参数
    public void invoke(Object o, Method m);
}
