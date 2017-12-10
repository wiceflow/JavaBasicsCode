package com.wiceflow.String;

/**
 * String 是不可变字符序列
 * Created by BF on 2017/8/31.
 */
public class TestString {
    public static void main(String[] args) {
        String str = new String("abcd");
        String str2 = new String("abcd");

        byte[] a = new byte[2];
        Byte b =  new Byte(String.valueOf(22));
        //equals 比较内容是否相等
        System.out.println(str.equals(str2));

        //trim 去除字符串首尾空格
        String str3 = "  aaab  ";
        System.out.println(str3);
        System.out.println(str3.trim());
        //String a = "abc";
        //String b = "abc";
        //System.out.println(a==b);
    }
}
