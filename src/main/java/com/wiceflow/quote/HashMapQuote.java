package com.wiceflow.quote;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BF
 * @date 2020/5/17
 * <p>
 * 关于 hashMap 中的引用
 *
 * <p> 1、探讨值传递与引用传递 </p>
 * <p> 2、强引用修改引用对象 </p>
 */
public class HashMapQuote {

    /**
     * 测试 HashMap 中的引用是 强引用
     *
     * 从下面的测试用例中可以看出
     * hashMap 中的 key 所指向的 value 是直接指向了 "test-map-one"，而不是 str1 这个对象
     * 所以在修改 str1 的值时候，并不会影响 hashMap 中的内容
     */
    @Test
    public void testStrongHashMapQuote() {

        Map<String, String> map1 = new HashMap<>();
        String str1 = "test-map-one";
        String str2 = "test-map-two";

        map1.put("str1", str1);
        map1.put("str2", str2);
        // {str1=test-map-one, str2=test-map-two}
        System.out.println(map1.toString());
        System.gc();
        str1 = null;
        System.gc();
        // {str1=test-map-one, str2=test-map-two}
        System.out.println(map1.toString());
        System.gc();
        str2 = "test-map-three";
        System.gc();
        // {str1=test-map-one, str2=test-map-two}
        System.out.println(map1.toString());
    }

    /**
     * 引用传递碰上强引用
     *  <p> 这个例子更好的说明，hashMap 中强引用至对象 </p>
     *
     *  而且在 animal 没有指向别的对象时，在修改它的时候，都会导致 hashMap 中的值改变
     *  由此可以看出 animal2 和 hashMap 都指向了同一个 对象
     */
    @Test
    public void testObjectQuote(){
        Animal animal = new Animal("大熊猫",1.36);
        Animal anima2 = new Animal("猫",0.30);
        Map<String,Animal> animalMap = new HashMap<>(2);
        animalMap.put("animal1",animal);
        animalMap.put("animal2",anima2);

        // {animal1=Animal{type='大熊猫', height=1.36}, animal2=Animal{type='猫', height=0.3}}
        System.out.println(animalMap);

        animal = null;
        // {animal1=Animal{type='大熊猫', height=1.36}, animal2=Animal{type='猫', height=0.3}}
        System.out.println(animalMap);

        anima2.setType("泰迪");
        // {animal1=Animal{type='大熊猫', height=1.36}, animal2=Animal{type='泰迪', height=0.3}}
        System.out.println(animalMap);

        anima2 = new Animal("毛毛虫",0.01);
        // {animal1=Animal{type='大熊猫', height=1.36}, animal2=Animal{type='泰迪', height=0.3}}
        System.out.println(animalMap);


    }
}
