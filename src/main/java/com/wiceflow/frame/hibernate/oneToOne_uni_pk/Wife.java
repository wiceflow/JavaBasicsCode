package com.wiceflow.frame.hibernate.oneToOne_uni_pk;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by BF on 2017/12/21.
 * 单向一对一  妻子
 */
@Entity
public class Wife {
    private int id ;
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
}
