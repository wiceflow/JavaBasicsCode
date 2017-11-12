package com.wiceflow.collection.iterator;

import java.util.*;

/**
 * Created by BF on 2017/11/6.
 * 迭代器测试
 */
public class Test01 {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

        Set set = new HashSet();
        set.add("aaa");
        set.add("bbb");
        set.add("ccc");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println((String) iterator.next());
        }

        // 迭代器遍历map
        Map map = new HashMap();
        map.put("a","aaa");
        map.put("b","bbb");
        map.put("c","ccc");
        map.put("d","ddd");
        map.put("e","eee");

        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        Iterator iterator1 = map.entrySet().iterator();
        while (iterator1.hasNext()){
            Map.Entry entry = (Map.Entry) iterator1.next();
            System.out.println("key= " + entry.getKey());
        }
    }
}
