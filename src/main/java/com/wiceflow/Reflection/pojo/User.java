package com.wiceflow.Reflection.pojo;

import java.io.Serializable;

/**
 * Created by BF on 2017/9/1.
 */
public class User implements Serializable {
    private Integer id;
    private Integer age;
    private String uname;


    public User(Integer id, Integer age, String uname) {
        this.id = id;
        this.age = age;
        this.uname = uname;
    }

    public User() {
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
