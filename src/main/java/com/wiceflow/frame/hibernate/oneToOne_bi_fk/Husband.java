package com.wiceflow.frame.hibernate.oneToOne_bi_fk;

import javax.persistence.*;

/**
 * Created by BF on 2017/12/21.
 * 双向外键关联
 */
@Entity
public class Husband {
    private int id ;
    private String name;
    private Wife wife;

    /**
     * 一对一 在Husband中设置Wife的外键
     * @return
     */
    @OneToOne
    @JoinColumn(name = "wifeId")
    public Wife getWife() {
        return wife;
    }

    public void setWife(Wife wife) {
        this.wife = wife;
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
