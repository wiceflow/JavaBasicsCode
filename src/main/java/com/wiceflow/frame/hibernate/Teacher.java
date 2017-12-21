package com.wiceflow.frame.hibernate;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by BF on 2017/12/20.
 * 联系Hibernate Teacher表
 */
@Entity
//@SequenceGenerator(name = "teacher_id_seq",sequenceName = "teacher_id_seq")
//@IdClass(TeacherPK.class) // 与复合键类相同
public class Teacher {
    //private TeacherPK teacherPK;
    private int id;
    private String name;
    private int age;
    private String gender;
    //@EmbeddedId
//    public TeacherPK getTeacherPK() {
//        return teacherPK;
//    }
//
//    public void setTeacherPK(TeacherPK teacherPK) {
//        this.teacherPK = teacherPK;
//    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
