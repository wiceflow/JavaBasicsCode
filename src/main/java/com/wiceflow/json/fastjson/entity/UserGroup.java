package com.wiceflow.json.fastjson.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BF on 2017/12/6.
 */
public class UserGroup {
    private String name;
    private List<User> list = new ArrayList<User>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "\r\nUserGroup{" +
                "name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}
