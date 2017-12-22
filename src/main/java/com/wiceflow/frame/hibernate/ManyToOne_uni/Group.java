package com.wiceflow.frame.hibernate.ManyToOne_uni;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by BF on 2017/12/22.
 * 多对一 一对多 双向
 */
@Entity
@Table(name = "t_group")
public class Group {
    private int id;
    private String NAME;
    private Set<User> users = new HashSet<>();

    // 双向必加映射
    @OneToMany(mappedBy = "group")
    @Cascade(value = CascadeType.ALL)
//    @JoinColumn(name = "groupId")
    public Set<User> getUsers() {
        return users;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
}
