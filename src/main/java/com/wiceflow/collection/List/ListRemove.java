package com.wiceflow.collection.List;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BF
 * @date 2018/10/8
 * 测试ArrayList删除操作
 */
public class ListRemove {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>(3);
        list1.add("name");
        list1.add("age");
        list1.add("phone");
        int a = 0;
        // 利用反编译可知，foreach循环会被转换成 while(iterator.hasNext())循环
        for (String str : list1){
            System.out.println(a++);
            if ("name".equals(str)){
                list1.remove(str);
            }
            System.out.println(list1.toString());
        }
    }
}
