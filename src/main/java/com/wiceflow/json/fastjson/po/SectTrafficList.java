package com.wiceflow.json.fastjson.po;

/**
 * Created by duxin on 2017/12/7.
 * 城市交通运行
 */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public double getIndex() {
        return Index;
    }

    public void setIndex(double index) {
        Index = index;
    }

    public double getLWSpeed() {
        return LWSpeed;
    }

    public void setLWSpeed(double LWSpeed) {
        this.LWSpeed = LWSpeed;
    }

    public String getSectName() {
        return SectName;
    }

    public void setSectName(String sectName) {
        SectName = sectName;
    }

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
