package com.wiceflow.collection.List;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BF on 2017/11/5.
 * ArrayList源码方法分析
 */
public class Test01 {
    public static void main(String[] args) {
        List list = new ArrayList();

        // 往数据存储数据
        list.add("aaa");
        list.add("bbb");
        list.add(123);
        // list数组的长度
        System.out.println(list.size());
        // 此时list中下标为1的是 bbb
        System.out.println(list.get(1));
        // list移除
        // list.remove(0);
        // 当list中位置元素被移除时候重复移除不会生效
        //list.remove("aaa");
        System.out.println(list.size());

        // 移除操作后bbb的下标变为0
        System.out.println(list.get(0));

        List list2 = new ArrayList();
        list2.add("ccc");
        list2.add("ddd");

        list.add(list2);

        System.out.println(list.size());
        // 当一个数组存在令一个数组中的时候，是以对象存储  长度不是想加  尚学堂有错！
        System.out.println(list.get(3));
        List list1 = (ArrayList) list.get(3);
        System.out.println(list1.get(0));

        // 覆盖
        list.set(1,"eee");
        System.out.println(list.size());
        System.out.println(list.get(1));
        System.out.println(list.get(2));
    }
}
