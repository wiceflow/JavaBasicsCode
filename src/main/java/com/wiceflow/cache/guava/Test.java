package com.wiceflow.cache.guava;

/**
 * @author BF
 * @date 2018/9/13
 */
public class Test {
    public static void main(String[] args){
        GuavaCachDemo cachDemo = new GuavaCachDemo();
        System.out.println("使用loadingCache");
        cachDemo.InitLoadingCache();

        System.out.println("使用loadingCache get方法  第一次加载");
        Man man = cachDemo.getCacheKeyloadingCache("001");
        System.out.println(man);

        System.out.println("\n使用loadingCache getIfPresent方法  第一次加载");
        man = cachDemo.getIfPresentloadingCache("002");
        System.out.println(man);

        System.out.println("\n使用loadingCache get方法  第一次加载");
        man = cachDemo.getCacheKeyloadingCache("002");
        System.out.println(man);

        System.out.println("\n使用loadingCache get方法  已加载过");
        man = cachDemo.getCacheKeyloadingCache("002");
        System.out.println(man);

        System.out.println("\n使用loadingCache get方法  已加载过,但是已经被剔除掉,验证重新加载");
        man = cachDemo.getCacheKeyloadingCache("001");
        System.out.println(man);

        System.out.println("\n使用loadingCache getIfPresent方法  已加载过");
        man = cachDemo.getIfPresentloadingCache("001");
        System.out.println(man);

        System.out.println("\n使用loadingCache put方法  再次get");
        Man newMan = new Man();
        newMan.setId("001");
        newMan.setName("额外添加");
        cachDemo.putloadingCache("001",newMan);
        man = cachDemo.getCacheKeyloadingCache("001");
        System.out.println(man);
    }
}
