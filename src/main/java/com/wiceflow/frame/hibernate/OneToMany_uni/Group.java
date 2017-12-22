package com.wiceflow.frame.hibernate.OneToMany_uni;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by BF on 2017/12/22.
 * 一对多单向
 */
@Entity
@Table(name = "t_group")
public class Group {
    private int id;
    private int NAME;
    // 使用set集合保证一致性
    private Set<User> users = new HashSet<>();
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

    @OneToMany
    @JoinColumn(name = "groupId")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
