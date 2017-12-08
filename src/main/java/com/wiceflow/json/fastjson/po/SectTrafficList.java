package com.wiceflow.json.fastjson.po;

import javax.persistence.*;
import javax.persistence.Basic;

/**
 * Created by duxin on 2017/12/7.
 * 城市交通运行
 */
@Entity
@Table(name = "\"SectTrafficList\"", schema = "public", catalog = "indexSystem")
public class SectTrafficList {
    // 主键
    private int id;
    // 外键
    private int pid;
    // 指数
    private double Index;
    // 早高峰上周速度
    private double LWSpeed;
    // 区域名称
    private String SectName;
    // 高峰时段速度
    private double Speed;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "\"pId\"")
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "\"Index\"")
    public double getIndex() {
        return Index;
    }
    public void setIndex(double index) {
        Index = index;
    }

    @Basic
    @Column(name = "\"LWSpeed\"")
    public double getLWSpeed() {
        return LWSpeed;
    }
    public void setLWSpeed(double LWSpeed) {
        this.LWSpeed = LWSpeed;
    }

    @Basic
    @Column(name = "\"SectName\"")
    public String getSectName() {
        return SectName;
    }
    public void setSectName(String sectName) {
        SectName = sectName;
    }

    @Basic
    @Column(name = "\"Speed\"")
    public double getSpeed() {
        return Speed;
    }
    public void setSpeed(double speed) {
        Speed = speed;
    }

    @Override
    public String toString() {
        return "\nSectTrafficList{" +
                " Index=" + Index +
                ", LWSpeed=" + LWSpeed +
                ", SectName='" + SectName + '\'' +
                ", Speed=" + Speed +
                '}';
    }
}
