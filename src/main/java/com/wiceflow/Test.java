package com.wiceflow;
import org.quartz.SchedulerException;
import java.text.ParseException;

import static com.wiceflow.http.retrofit2.BDindex.ObtainData;
import static com.wiceflow.http.retrofit2.BDindex.getData;



/**
 * Created by BF on 2017/12/12.
 * 常用测试
 */
public class Test {

}
class Employee {
    public int age;
}
class Main {
    public static void changeEmployee(Employee employee)
    {
        employee = new Employee();
        employee.age = 1000;
    }

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.age = 100;
        changeEmployee(employee);
        System.out.println(employee.age);

    }
}
