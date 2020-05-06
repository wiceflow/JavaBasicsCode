package com.wiceflow.Io.File;

import java.io.File;

/**
 * 输出子孙级目录|文件的名称(绝对路径)
 * 1、listFiles()
 * 2、递归
 * static listRoots() 根路径
 * Created by BF on 2017/9/21.
 */
public class Demo {
    public static void main(String[] args) {
        String path = "E:/file/test/iceflow.jpg";
        File parent = new File(path);
        printName(parent);

//        File[] roots = File.listRoots();
//        System.out.println(Arrays.toString(roots));
//        for (File temp : roots) {
//            printName(temp);
//        }
    }

    /**
     * 输出路径
     */
    public static void printName(File src) {
        if (null == src || !src.exists()) {
            return;
        }
        System.out.println(src.getAbsolutePath());
        if (src.isDirectory()) { //文件夹
            for (File sub : src.listFiles()) {
                printName(sub);
            }
        }
    }
}
