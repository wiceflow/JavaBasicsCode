package com.wiceflow.GenericParadigm.gen03;

/**
 * @author BF
 * @date 2018/5/8
 * 泛型方法 - JAVA核心技术
 */
public class demo01 {
    public static void main(String[] args) {
        // 省略了<String>类型参数
        // String middle = ArrayAlg.<String>getMiddle("John","Q","Public");
        String middle = ArrayAlg.getMiddle("John", "Q", "Public");
        System.out.println(middle);

        // 编译器会自动检查类型是否一致
        // double next = ArrayAlg.getMiddle(3.14,1729,0);   这是一个错误的代码
        double next = ArrayAlg.getMiddle(3.14, 1729.0, 0.0);
        System.out.println(next);
    }
}

class ArrayAlg {
    public static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }
}
