package com.wiceflow.frame.hibernate.ManyToMany_bi;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by BF on 2017/12/22.
 * 多对多单向
 */
@Entity
public class Student {
    private int id;
    private String name;
    private Set<Teacher> teachers = new HashSet<>();

    @ManyToMany(mappedBy = "students")
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

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
