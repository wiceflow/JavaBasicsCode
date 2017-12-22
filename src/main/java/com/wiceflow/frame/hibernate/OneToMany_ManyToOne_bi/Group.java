package com.wiceflow.frame.hibernate.OneToMany_ManyToOne_bi;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by BF on 2017/12/22.
 * 多对一 单向
 */
@Entity
@Table(name = "t_group")
public class Group {
    private int id;
    private int NAME;
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNAME() {
        return NAME;
    }

    public void setNAME(int NAME) {
        this.NAME = NAME;
    }
}
