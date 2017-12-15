package com.wiceflow.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by BF on 2017/12/14.
 * List 工具类
 */
public class ListUtil<T> {
    private static final Logger _LOG = LoggerFactory.getLogger(ListUtil.class);
    private List<T> arrayList = new ArrayList<>();
    private List<T> linkedList = new LinkedList<>();

    /**
     * 用来将一个对象封装成List
     * 在hibernate多对一中存储用到
     * @param t
     * @return
     */
    public List<T> returnArrayList(T t){
        arrayList.add(t);
        return arrayList;
    }

    /**
     * 适合list中只有一个元素，从其中取出转为Entity
     * @param list
     * @return
     */
    public T  returnObject(List<T> list){
        T t = list.get(0);
        return t;
    }
}
