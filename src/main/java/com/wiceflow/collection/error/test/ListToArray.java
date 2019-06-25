package com.wiceflow.collection.error.test;

import com.wiceflow.Proxy.extendOrPolymerization.A;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BF
 * @date 2019/6/25 10:33
 * 集合转数组问题
 * <p> 集合转数组，注意泛型的丢失，应先创建好等同大小的数组 </p>
 */
public class ListToArray {


    public static void main(String[] args) {
        ListToArray listToArray = new ListToArray();
        listToArray.listToArray();
    }


    public void listToArray(){
        List<String> list = new ArrayList<>(3);
        list.add("one");
        list.add("two");
        list.add("three");

        // 泛型丢失，无法使用 String[] 接收无参方法返回的结果
        // 转换成功
        Object[] objects = list.toArray();


        // 数组长度小于 list 长度
        String[] lengthTwo = new String[2];
        list.toArray(lengthTwo);
        // null
        System.out.println(lengthTwo[1]);
        // null null
        System.out.println(Arrays.asList(lengthTwo));


        // 数组长度等于 list 长度
        String[] lengthThree = new String[3];
        list.toArray(lengthThree);
        System.out.println(lengthThree[1]);
        System.out.println(Arrays.asList(lengthThree));

    }
}
