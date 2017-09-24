package com.wiceflow.Io.File;

import java.io.File;

/**
 * 两个常量
 * 1、路径分隔符  ;
 * 2、名称分隔符 /(windows)  /(linux 等)
 * Created by BF on 2017/9/21.
 */
public class Demo01 {
    public static void main(String[] args) {
        System.out.println(File.pathSeparator);
        System.out.println(File.separator);
        //路径表示形式
        String path ="E:\\xp\\test\\2.jpg";
        path="E:"+File.separator+"xp"+File.separator+"test"+File.separator+"2.jpg";
        //推荐方式
        path="E:/xp/test/2.jpg";
        System.out.println("哈哈");
    }
}