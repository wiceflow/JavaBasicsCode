package com.wiceflow.Reflection.demo;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author BF
 * @date 2018/7/10
 */
public class ReflectDemoTest {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        // 获取项目路径
        System.out.println(System.getProperty("user.dir"));
        // 获取包路径
        System.out.println(ReflectDemoTest.class.getPackage().getName());
        String path = (System.getProperty("user.dir") + "\\" + "src\\main\\java\\" + ReflectDemoTest.class.getPackage().getName());
        System.out.println(path);
        path = path.replace(".","\\");
        System.out.println(path);


        properties.load(new FileReader(new File(path + "\\" + "class.txt")));


        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");

        Class<?> c = Class.forName(className);
        // 构造函数
        Constructor<?> con = c.getDeclaredConstructor();
        // 通过无参构造方法来构造对象
        Object obj = con.newInstance();
        // 通过反射获取对应方法
        Method method = c.getDeclaredMethod(methodName);
        // 方法调用
        method.invoke(obj);
    }
}
