package com.wiceflow.frame.hibernate;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by BF on 2017/12/20.
 * Teacher联合主键
 * 实现Serializable接口
 * 重写equals方法，重写hashcode方法
 */
//@Embeddable  当对应PO类上使用 EmbeddedId和IDClass时候 这里不需要注解
public class TeacherPK implements Serializable{
    private int id;
    private String name;

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
    public boolean equals(Object o) {
        if (o instanceof TeacherPK){
            TeacherPK t = (TeacherPK)o;
            if (t.getId()==this.id&&t.getName().equals(this.name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
