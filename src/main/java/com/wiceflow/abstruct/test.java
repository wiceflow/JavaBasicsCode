package com.wiceflow.abstruct;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BF
 * @date 2018/9/14
 */
public class test {
    public static void main(String[] args) {
        AbstractMap a = new MapA();
        a.setAaa(new HashMap<String, String>());
        Map<String,String> map1 = a.getAaa();
        System.out.println(map1.get("name"));

        AbstractMap b = new MapB();
        b.setAaa(new HashMap<String, String>());
        Map<String,String> map2 = b.getAaa();
        System.out.println(map2.get("name"));
        Map<String,String> map3 = a.getAaa();
        System.out.println(map3.get("name"));
    }
}
