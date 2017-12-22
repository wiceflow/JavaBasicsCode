package com.wiceflow.frame.hibernate.ManyToMany_uni;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by BF on 2017/12/22.
 * 多对多单向
 */
@Entity
public class Student {
    private int id;
    private String name;
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
