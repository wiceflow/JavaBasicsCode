package com.wiceflow.json.fastjson.po;

import javax.persistence.*;
import javax.persistence.Basic;

/**
 * Created by duxin on 2017/12/7.
 * 关卡运行
 */
@Entity
@Table(name = "\"CrossTrafficList\"", schema = "public", catalog = "indexSystem")
public class CrossTrafficList {

    // 主键
    private int id;
    // 外键
    private int pId;
    // 早高峰速度
    private double AMSpeed;
    // 关口名称
    private String CrossName;
    // 晚高峰出关速度
    private double PMSpeed;
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
        return pId;
    }
    public void setPid(int pid) {
        this.pId = pid;
    }

    @Basic
    @Column(name = "\"AMSpeed\"")
    public double getAMSpeed() {
        return AMSpeed;
    }
    public void setAMSpeed(double AMSpeed) {
        this.AMSpeed = AMSpeed;
    }

    @Basic
    @Column(name = "\"CrossName\"")
    public String getCrossName() {
        return CrossName;
    }
    public void setCrossName(String crossName) {
        CrossName = crossName;
    }

    @Basic
    @Column(name = "\"PMSpeed\"")
    public double getPMSpeed() {
        return PMSpeed;
    }
    public void setPMSpeed(double PMSpeed) {
        this.PMSpeed = PMSpeed;
    }

    @Override
    public String toString() {
        return "\nCrossTrafficList{" +
                " AMSpeed=" + AMSpeed +
                ", CrossName='" + CrossName + '\'' +
                ", PMSpeed=" + PMSpeed +
                '}';
    }
}
