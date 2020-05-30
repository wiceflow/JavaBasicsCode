package com.wiceflow.quote;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author BF
 * @date 2020/5/17
 * <p>
 * 弱引用测试
 * 1、WeakHashMap
 * 2、HashMap
 * <p> 趁机学习对象的引用 </p>
 * <p>
 * 可以先看前言  HashMap
 */
public class WeakHashMapQuote {

    public static void main(String[] args) {

        WeakHashMapQuote quote = new WeakHashMapQuote();
        quote.strongQuote();


    }

    /**
     * 强引用的 hashMap 赋值对比
     */
    public void strongQuote() {
        House house1 = new House("一号房源");
        People people1 = new People("BF", 24);

        House house2 = new House("二号房源");
        People people2 = new People("YN", 23);

        // hashMap 强引用
        Map<People, House> strongMap = new HashMap<>(2);
        strongMap.put(people1, house1);
        strongMap.put(people2, house2);
        // size =2
        System.out.println(strongMap.toString());
        // 触发 gc
        people1 = null;
        System.gc();
        System.runFinalization();
        // strongMap 中还是存在两个元素
        System.out.println(strongMap.toString());
        // 因为 people1 指向了 null，所以这里不能再获取到元素了
        System.out.println(strongMap.get(people1));
        System.out.println(strongMap.get(people2));

        // 通过遍历可以发现，strongMap 中的元素未曾发生改变
        Set<People> peopleSet = strongMap.keySet();
        System.out.println(peopleSet);
        for (People people : peopleSet) {
            System.out.println(people.toString());
            System.out.println(strongMap.get(people));
        }
    }
}
