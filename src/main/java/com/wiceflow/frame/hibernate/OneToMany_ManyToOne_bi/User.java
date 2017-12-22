package com.wiceflow.frame.hibernate.OneToMany_ManyToOne_bi;

import javax.persistence.*;

/**
 * Created by BF on 2017/12/22.
 * 多对一 单向 用户 多的一方
 */
@Entity
@Table(name = "t_user")
public class User {
    private int id;
    private String name;
    private Group group;

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

    @ManyToOne
    @JoinColumn(name = "groupId")
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
