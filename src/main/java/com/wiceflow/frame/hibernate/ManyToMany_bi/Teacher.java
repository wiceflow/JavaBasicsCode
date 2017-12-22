package com.wiceflow.frame.hibernate.ManyToMany_bi;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by BF on 2017/12/22.
 * 多对多 单向
 */
@Entity
public class Teacher {
    private int id;
    private int name;
    private Set<Student> students = new HashSet<>();
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
    @ManyToMany
    @JoinTable(name = "t_s",
            joinColumns = {@JoinColumn(name = "teacher_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
