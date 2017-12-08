package com.wiceflow.json.fastjson.po;

import javax.persistence.*;
import javax.persistence.Basic;

/**
 * Created by duxin on 2017/12/7.
 * 整个交通情况
 */
@Entity
@Table(name = "\"WholeTrafficList\"", schema = "public", catalog = "indexSystem")
public class WholeTrafficList {
    // 主键
    private int id;
    // 外键
    private int pid;
    // 起止时间（例如：11.6-11.12）
    private String SEDate;
    // 全市全时段速度
    private double Speed;
    // 全市高峰时段速度
    private double SpeedPeak;
    // 周数
    private double WeekNo;
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
    @Column(name = "\"SEDate\"")
    public String getSEDate() {
        return SEDate;
    }
    public void setSEDate(String SEDate) {
        this.SEDate = SEDate;
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
    @Column(name = "\"SpeedPeak\"")
    public double getSpeedPeak() {
        return SpeedPeak;
    }
    public void setSpeedPeak(double speedPeak) {
        SpeedPeak = speedPeak;
    }

    @Basic
    @Column(name = "\"WeekNo\"")
    public double getWeekNo() {
        return WeekNo;
    }
    public void setWeekNo(double weekNo) {
        WeekNo = weekNo;
    }

    @Override
    public String toString() {
        return "\nWholeTrafficList{" +
                " SEDate='" + SEDate + '\'' +
                ", Speed=" + Speed +
                ", SpeedPeak=" + SpeedPeak +
                ", WeekNo=" + WeekNo +
                '}';
    }
}
