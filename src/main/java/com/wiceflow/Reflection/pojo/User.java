package com.wiceflow.Reflection.pojo;

import java.io.Serializable;

/**
 * Created by BF on 2017/9/1.
 */
public class User implements Serializable {
    private int id;
    private int age;
    private String uname;


    public User(int id, int age, String uname) {
        this.id = id;
        this.age = age;
        this.uname = uname;
    }

    public User() {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
