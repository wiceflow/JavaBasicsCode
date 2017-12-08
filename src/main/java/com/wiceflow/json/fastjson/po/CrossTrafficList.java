package com.wiceflow.json.fastjson.po;

/**
 * Created by duxin on 2017/12/7.
 * 关卡运行
 */
public class CrossTrafficList {

    // 主键
    private int id;
    // 外键
    private int pid;
    // 早高峰速度
    private double AMSpeed;
    // 关口名称
    private String CrossName;
    // 晚高峰出关速度
    private double PMSpeed;

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

    public double getAMSpeed() {
        return AMSpeed;
    }

    public void setAMSpeed(double AMSpeed) {
        this.AMSpeed = AMSpeed;
    }

    public String getCrossName() {
        return CrossName;
    }

    public void setCrossName(String crossName) {
        CrossName = crossName;
    }

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
