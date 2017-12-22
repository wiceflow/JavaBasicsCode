package com.wiceflow.frame.hibernate.OneToMany_uni;

import javax.persistence.*;

/**
 * Created by BF on 2017/12/22.
 * 一对多 单向 用户 多的一方
 */
@Entity
@Table(name = "t_user")
public class User {
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
