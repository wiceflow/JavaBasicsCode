package com.wiceflow.collection.map;

import com.wiceflow.collection.List.myLinkList.MyLinkList;

/**
 * Created by BF on 2017/11/6.
 * 自定义Map升级版
 * 1.提高查询的效率  利用hash算法精简
 * Map 底层结构就是数组加链表
 */
public class MyMap002 {
    MyLinkList[] arr = new MyLinkList[999];

    int size;

    public void put(Object key,Object value){
        SxtEntry e = new SxtEntry(key,value);

        // 可能为负数
        int hash = key.hashCode();
        hash = hash<0?-hash:hash;

        int h = hash%999;

        if (arr[h]==null){
            MyLinkList list = new MyLinkList();
            list.add(e);
            arr[h] = list;
            size++;
        }else {
            MyLinkList list = arr[h];
            for (int i=0;i<list.size();i++){
                SxtEntry e2 = (SxtEntry) list.get(i);
                if (e2.key.equals(key)){
                    e2.value = value;
                    return;
                }
            }
            arr[h].add(e);
        }
    }

    /**
     * 模拟HashMap get方法 数组链表查询
     * @param key
     * @return
     */
    public Object get(Object key){
        int h = key.hashCode()%999;
        if (arr[h]!=null){
            if (arr[h].size()>1){
                for (int i=0;i<arr[h].size();i++){
                    if (((SxtEntry) arr[h].get(i)).key.equals(key)){
                        return ((SxtEntry) arr[h].get(i)).value;
                    }
                }
            }else {
                return ((SxtEntry) arr[h].get(0)).value;
            }
        }

        return null;
    }
    public static void main(String[] args) {
        MyMap002 a = new MyMap002();
        a.put("冰峰","燕妮");
        a.put("张三","杨幂");
        a.put("李四","单身");
        a.put("张三","单身");
        System.out.println(a.size);
        System.out.println(a.get("张三"));
        System.out.println(a.get("冰峰"));
    }
}
