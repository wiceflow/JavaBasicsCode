package com.wiceflow.cache.guava.test;

import com.wiceflow.cache.guava.AbstractGuavaCacheService;

/**
 * @author BF
 * @date 2020/8/13 17:14
 */
public class StringCache extends AbstractGuavaCacheService<String,String> {
    @Override
    protected void init() {

    }

    @Override
    protected void loadValueWhenStarted() {

    }

    @Override
    protected String getValueWhenExpired(String key) throws Exception {
        System.out.println("触发加载缓存");
        // 这句话并不需要
//        this.put(key,"cache put");
        return "cache put";
    }

    @Override
    protected String reloadValue(String key) throws Exception {

        System.out.println("刷新缓存");
        return null;
    }
}
