package com.wiceflow.frame.hibernate.component;
/**
 * Created by BF on 2017/12/21.
 * 组合表 作为Husband的一部分 不需要设置其他东西
 */
public class Wife {
    private String wifeName;
    private int age;

    public String getWifeName() {
        return wifeName;
    }

    public void setWifeName(String wifeName) {
        this.wifeName = wifeName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
