package com.wiceflow.JVM;

/**
 * 测试自定义加载器
 * Created by BF on 2017/9/18.
 */
public class testLoader {
    public static void main(String[] args) throws Exception {
        FileSystemClassLoader loader1 = new FileSystemClassLoader("D:/myjava");
        FileSystemClassLoader loader2 = new FileSystemClassLoader("D:/myjava");

        Class<?> c1 = loader1.loadClass("com.wiceflow.HelloWord");
        Class<?> c2 = loader1.loadClass("com.wiceflow.HelloWord");
        Class<?> c3 = loader2.loadClass("com.wiceflow.HelloWord");
        Class<?> c4 = loader2.loadClass("java.lang.String");
        Class<?> c5 = loader2.loadClass("com.wiceflow.JVM.demo01");


        System.out.println(c1.hashCode());  // 564321321
        System.out.println(c2.hashCode());  // 564321321
        System.out.println(c3.hashCode());  // 245121248
        System.out.println(c4.hashCode());  // 1956725890
        System.out.println(c5.hashCode());  // 356573597

        System.out.println(c4.getClassLoader()); // 引导类加载器 null
        System.out.println(c3.getClassLoader()); // 自定义类加载器
        System.out.println(c5.getClassLoader()); // 应用程序类加载器

        System.out.println(c1.hashCode()==c2.hashCode());
        System.out.println(c1.hashCode()==c3.hashCode());

    }
}
