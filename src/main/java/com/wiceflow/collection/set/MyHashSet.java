package com.wiceflow.collection.set;

import java.util.HashMap;

/**
 * Created by BF on 2017/11/6.
 * 模拟HashSet实现
 * 底层是一个HashMap数组，存储的值为Map的key 所以set不可重复
 */
public class MyHashSet {

    HashMap map;

    // 因为set中的值存放在Map的key中，所以map的value同意设置为一个常量
    private static final Object PRESENT = new Object();

    public int size(){
        return map.size();
    }

    public MyHashSet() {
        map = new HashMap();
    }

    /**
     * hashMap 新增方法  类似
     * @param o
     */
    public void add(Object o){
        map.put(o,PRESENT); //set的不可重复就是利用了map的键不可重复
    }

    public void remove(Object o){
        map.remove(o);
    }
    public static void main(String[] args) {
        MyHashSet set = new MyHashSet();
        set.add("aaa");
        set.add(new String("aaa"));
        System.out.println(set.size());
    }
}
