package com.wiceflow.frame.hibernate.CRUDTest;

import javax.persistence.*;

/**
 * Created by BF on 2017/12/22.
 * 测定
 */
@Entity
@Table(name = "t_user")
public class User {
    private int id;
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
