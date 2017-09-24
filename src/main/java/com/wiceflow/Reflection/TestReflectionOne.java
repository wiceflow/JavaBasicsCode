package com.wiceflow.Reflection;

/**
 * Created by BF on 2017/9/1.
 * 反射
 * 测试 java.lang.Class对象的获取方式
 */
public class TestReflectionOne {
    public static void main(String[] args) {
        String path = "com.wiceflow.Reflection.pojo.User";

        try {
            Class clazz = Class.forName(path);

            // 对象是表示或封装一些数据。一个类被加载后,JVM会创建一个对应该类的Class对象,类的整个结构信息会放到对应的Class对象中
            // 这个Class对象就像一面镜子一样,通过这面镜子我可以看到对应类的全部信息
            System.out.println(clazz.hashCode());   // 1163157884

            // 一个类只会被加载一次，所以都是同一个对象
            Class clazz2 = Class.forName(path);
            System.out.println(clazz2.hashCode());  // 1163157884

            // 定义String类型的变量，反射后也是String类型的
            Class clazz3 = String.class;
            System.out.println(clazz3.hashCode());  // 1956725890
            Class clazz4 = path.getClass();
            System.out.println(clazz4.hashCode());  // 1956725890

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
