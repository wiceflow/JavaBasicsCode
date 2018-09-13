package com.wiceflow;

import com.alibaba.fastjson.JSON;
import com.wiceflow.url.HttpURLConnectionUtil;
import com.wiceflow.util.DateUtil;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by BF on 2017/12/12.
 * 常用测试
 */
public class Test {

    public static void main(String[] args) throws ParseException {
//        StringBuilder builder = new StringBuilder();
//        List<String> list = new ArrayList<>();
//        testStringBudiler(builder, list);
//        System.out.println(builder.toString());
//        System.out.println("----");
//        System.out.println(list);
//
//        List<String> list1 = new ArrayList<>();
//        System.out.println(list1 == null ? 0 : 1);
//
//        Map<String, Integer> map = new HashMap<>();
//        System.out.println(map.get("11"));
//        System.out.println(DateUtil.getHourAndMin());



//
//        People people = new People();
//        people.setAge("8");
//        System.out.println(people.getAge());
//        System.out.println(people.getBus());
//
//        people.setName(null);
//        System.out.println(people.getName());
//        System.out.println(people.getBus());
//        String s = people.getName();
//        System.out.println(s);

//        String string = "2016-10-24 21:59:06";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.parse(string));



//        System.out.println(UUID.randomUUID().toString());
        Map<String,Boolean> map = new HashMap<>();
        System.out.println(map.get("a"));
    }


    public static void testStringBudiler(StringBuilder builder, List<String> list) {
        list.add("sucess");
        builder.append("成功");
        builder.append("aaa");
    }
}

class People {
    String name;
    String age;

    List<String> bus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<String> getBus() {
        return bus;
    }

    public void setBus(List<String> bus) {
        this.bus = bus;
    }
}