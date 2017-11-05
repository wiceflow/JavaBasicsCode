package com.wiceflow.Proxy;

/**
 *
 * Created by BF on 2017/10/11.
 */
//public class Test {
//    public static void main(String[] args) {
//        System.out.println(System.getProperty("user.dir"));
//        System.out.println(System.getProperty("java.class.path"));
//
//    }
//}
public class Test {
    public static void main(String[] args) {
        Object o = new Object() {
            public boolean equals(Object obj) {
                return true;
            }
        };
        System.out.println(o.equals("Fred"));
    }
}