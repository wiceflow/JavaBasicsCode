package com.wiceflow.frame.hibernate;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by duxin on 2017/12/7.
 */
@Entity
@Table(name = "user", schema = "public", catalog = "testHibernate")
public class UserEntity {
    private int id;
    private String name;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq")
    @Column(name = "id", nullable = false, length = 255)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
