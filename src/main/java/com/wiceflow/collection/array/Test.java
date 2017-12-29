package com.wiceflow.collection.array;

/**
 * Created by BF on 2017/12/25.
 * 测试启动
 */
public class Test {
    public static void main(String[] args) {
//        Manager manager = new Manager();
//        manager.setName("文冰峰");
//        manager.setPrice(500);
//        System.out.println(manager.getPrice());
        Manager m1 = new Manager();

        Manager[] manager = new Manager[10];
        Employee[] employees = manager;
        employees[0] = new Employee();  // 会报错
        System.out.println(m1.equals(employees[0]));

    }
}
