package com.wiceflow.GenericParadigm.gen01;

import java.io.Closeable;
import java.io.IOException;

/**
 * 泛型方法<> <>写在修饰符的后面，返回类型的前面
 * 只能访问对象的信息，不能修改信息
 * Created by BF on 2017/9/19.
 * <p>
 * 注意: 泛型还可以定义在方法中，是否拥有泛型方法，与其所在的类是否泛型没有关系
 */
public class TestMethod {
    public static void main(String[] args) {
        test("a");
    }

    // 泛型方法   区分泛型方法与静态方法
    public static <T> void test(T a) {
        System.out.println(a);
    }

    // extends <= 相当于继承
    // T... 相当于可变参数
    public static <T extends Closeable> void test(T... a) {
        for (T temp : a) {
            try {
                if (temp != null) {
                    temp.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
