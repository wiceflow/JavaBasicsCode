package com.wiceflow.frame.hibernate.oneToOne_bi_fk;

import javax.persistence.*;

/**
 * Created by BF on 2017/12/21.
 * 双向外键关联
 */
@Entity
public class Wife {
    private int id ;
    private String name;
    private Husband husband;

    @OneToOne(mappedBy = "wife")
    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }

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
