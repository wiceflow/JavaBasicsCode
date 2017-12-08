package com.wiceflow.json.fastjson.po;

import javax.persistence.*;
import javax.persistence.Basic;

/**
 * Created by duxin on 2017/12/7.
 * 停车收费城区交通运行
 */
@Entity
@Table(name = "\"ParkTrafficList\"", schema = "public", catalog = "indexSystem")
public class ParkTrafficList {
    // 主键
    private int id;
    // 外键
    private int pid;
    // 行政区
    private String ParkName;
    // 工作日收费时段平均速度
    private double Speed;
    // 与实施前相比
    private double SpeedBefore;
    // 环比
    private double SpeedMOM;
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
    @Column(name = "\"ParkName\"")
    public String getParkName() {
        return ParkName;
    }
    public void setParkName(String parkName) {
        ParkName = parkName;
    }

    @Basic
    @Column(name = "\"Speed\"")
    public double getSpeed() {
        return Speed;
    }
    public void setSpeed(double speed) {
        Speed = speed;
    }

    @Basic
    @Column(name = "\"SpeedBefore\"")
    public double getSpeedBefore() {
        return SpeedBefore;
    }
    public void setSpeedBefore(double speedBefore) {
        SpeedBefore = speedBefore;
    }

    @Basic
    @Column(name = "\"SpeedMOM\"")
    public double getSpeedMOM() {
        return SpeedMOM;
    }
    public void setSpeedMOM(double speedMOM) {
        SpeedMOM = speedMOM;
    }

    @Override
    public String toString() {
        return "\nParkTrafficList{" +
                " ParkName='" + ParkName + '\'' +
                ", Speed=" + Speed +
                ", SpeedBefore=" + SpeedBefore +
                ", SpeedMOM=" + SpeedMOM +
                '}';
    }
}
