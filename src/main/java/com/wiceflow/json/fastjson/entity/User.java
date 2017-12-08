package com.wiceflow.json.fastjson.entity;

/**
 * Created by BF on 2017/12/6.
 */
public class User {
    private int age;
    private String name;
    private String dept;

    public com.wiceflow.json.fastjson.entity.Iceflow getIceflow() {
        return iceflow;
    }

    public void setIceflow(Iceflow iceflow) {
        this.iceflow = iceflow;
    }

    private Iceflow iceflow;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "\r\nUser{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", iceflow=" + iceflow +
                '}';
    }
}
