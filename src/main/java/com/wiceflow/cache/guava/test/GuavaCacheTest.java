package com.wiceflow.cache.guava.test;

/**
 * @author BF
 * @date 2020/8/13 17:18
 */
public class GuavaCacheTest {

    public static void main(String[] args) throws InterruptedException {
        StringCache stringCache = new StringCache();
        while (true) {
            String str = stringCache.getValue("cache");
            System.out.println(str);
            Thread.sleep(1000);
        }
    }
}
