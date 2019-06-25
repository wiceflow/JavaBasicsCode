package com.wiceflow.collection.error.test;

import com.wiceflow.Proxy.extendOrPolymerization.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BF
 * @date 2019/6/21 10:01
 * 数组转 list 的问题 TODO 记得做笔记
 * <p> 数组转 list，list 不可进行添加操作，也不可以进行删除/清空操作，当修改了 list 里面的元素，对应原数据的数据也会跟着改变</p>
 * <p> 数组在创建的时候，默认是创建了数据为 null 的数组，并不是空数据 </p>
 * <p> 使用 new 方法创建新数组，则需要初始化大小，如果填的是负数 eg：new String[3] 它在编译器不会出现异常，但运行时会抛出异常 NegativeArraySizeException </p>
 * <p> 具体解释在 159 页 </p>
 */
public class ArrayToList {

    public static void main(String[] args) {
        ArrayToList arrayToList = new ArrayToList();
//        arrayToList.arrayToListTestOne();
        arrayToList.arrayToListTestTwo();
    }


    private void arrayToListTestOne(){
        // array 的大小为 2
        String[] strings = {"first","last"};
        System.out.println(strings.length);

        List<String> list = Arrays.asList(strings);
        System.out.println(list);
        System.out.println(list.size());

        list.set(0,"new First");

        System.out.println(list);
        System.out.println(strings[0]);

        list.add("add new last");

        System.out.println(list);
    }


    private void arrayToListTestTwo(){
        // array 的大小为 3  这个样子初始化是赋值了 null
        String[] strings = new String[3];
        strings[0] = "first";
        strings[1] = "last";
        System.out.println(strings.length);

        List<String> list = Arrays.asList(strings);
        System.out.println(list);
        System.out.println(list.size());

        list.set(0,"new First");

        System.out.println(list);
        System.out.println(strings[0]);

        list.add("add new last");

//        list.clear();
//        list.remove(3);
        System.out.println(list);
    }


}
