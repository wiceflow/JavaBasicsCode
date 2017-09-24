package com.wiceflow.Io.File;

import java.io.File;

/**
 * 相对路径与绝对路径构造 File对象
 * 1、相对路径
 * File(String parent, String child)  ==>File("E:/xp/test","2.jpg")
 * File(File parent, String child)     ==> File(new File("E:/xp/test"),"2.jpg")
 * 2、绝对路径
 * File(String name);
 * Created by BF on 2017/9/21.
 */
public class Demo02 {
    public static void main(String[] args) {
        String parentPath = "E:/xp/test";
        String name = "2.jpg";

        //相对路径
        File src = new File(parentPath, name);
        //src = new File(new File(parentPath), name);
        //输出
        System.out.println(src.getName());
        System.out.println(src.getPath());
        //绝对路径

        src = new File("E:/xp/test/2.jpg");
        System.out.println(src.getName());
        System.out.println(src.getPath());
        //没有盘符: 以 user.dir构建 当前项目路径
        src = new File("test.txt");
        src = new File(".");
        System.out.println(src.getName());
        System.out.println(src.getPath());
        System.out.println(src.getAbsolutePath());
    }
}