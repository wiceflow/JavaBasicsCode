package com.wiceflow.StringBuilder;

/**
 * 可变字符序列,StringBuilder,StringBuffer  [StringBuffer是线程安全的,不过效率低]
 * Created by BF on 2017/8/31.
 */
public class TestStringBuider {
    public static void main(String[] args) {
        //StringBuilder sb = new StringBuilder();
        //
        //StringBuilder str1 = new StringBuilder("abc");
        //System.out.println(str1);
        //str1.append("efg"); //拼接上去 不会消除以前的
        //System.out.println(str1);
        //1:调用无参数构造器
        //StringBuffer str = new StringBuffer();
        //str.append("12345");
        //System.out.println(str.capacity());//16
        //System.out.println(str.length());//5
        //str.append("67890123456");
        //System.out.println(str.capacity());//16
        //System.out.println(str.length());//16
        //str.append("1");
        //System.out.println(str.capacity());//34
        //System.out.println(str.length());//17
        ////2:调用有参数构造器
        //str = new StringBuffer("123");
        //System.out.println(str.capacity());//19
        //System.out.println(str.length());//3
        StringBuilder stringBuilder = new StringBuilder("asdasjkhjksdhjk");
        stringBuilder.delete(3,5);
        System.out.println(stringBuilder);  //asdjkhjksdhjk
        stringBuilder.reverse();
        System.out.println(stringBuilder);  //kjhdskjhkjdsa
    }
}
