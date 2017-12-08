package com.wiceflow.json.fastjson.po;

/**
 * Created by duxin on 2017/12/7.
 * 主要干道交通运行
 */
public class ArterialTrafficList {
    // 主键
    private int id;
    // 外键
    private int pId;
    // 早高峰上周
    private double AMLWSpeed;
    // 早高峰同比周
    private double AMLYSpeed;
    // 早高峰速度
    private double AMSpeed;
    // 早高峰环比(+,-)
    private double AMSpeedMOM;
    // 干道名称
    private String ArterialName;
    // 晚高峰上周速度
    private double PMLWSpeed;
    // 晚高峰同比周速度
    private double PMLYSpeed;
    // 晚高峰速度
    private double PMSpeed;
    // 晚高峰环比
    private double PMSpeedMOM;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public double getAMLWSpeed() {
        return AMLWSpeed;
    }

    public void setAMLWSpeed(double AMLWSpeed) {
        this.AMLWSpeed = AMLWSpeed;
    }

    public double getAMLYSpeed() {
        return AMLYSpeed;
    }

    public void setAMLYSpeed(double AMLYSpeed) {
        this.AMLYSpeed = AMLYSpeed;
    }

    public double getAMSpeed() {
        return AMSpeed;
    }

    public void setAMSpeed(double AMSpeed) {
        this.AMSpeed = AMSpeed;
    }

    public double getAMSpeedMOM() {
        return AMSpeedMOM;
    }

    public void setAMSpeedMOM(double AMSpeedMOM) {
        this.AMSpeedMOM = AMSpeedMOM;
    }

    public String getArterialName() {
        return ArterialName;
    }

    public void setArterialName(String arterialName) {
        ArterialName = arterialName;
    }

    public double getPMLWSpeed() {
        return PMLWSpeed;
    }

    public void setPMLWSpeed(double PMLWSpeed) {
        this.PMLWSpeed = PMLWSpeed;
    }

    public double getPMLYSpeed() {
        return PMLYSpeed;
    }

    public void setPMLYSpeed(double PMLYSpeed) {
        this.PMLYSpeed = PMLYSpeed;
    }

    public double getPMSpeed() {
        return PMSpeed;
    }

    public void setPMSpeed(double PMSpeed) {
        this.PMSpeed = PMSpeed;
    }

    public double getPMSpeedMOM() {
        return PMSpeedMOM;
    }

    public void setPMSpeedMOM(double PMSpeedMOM) {
        this.PMSpeedMOM = PMSpeedMOM;
    }

    @Override
    public String toString() {
        return "\rArterialTrafficList{" +
                " AMLWSpeed=" + AMLWSpeed +
                ", AMLYSpeed=" + AMLYSpeed +
                ", AMSpeed=" + AMSpeed +
                ", AMSpeedMOM=" + AMSpeedMOM +
                ", ArterialName='" + ArterialName + '\'' +
                ", PMLWSpeed=" + PMLWSpeed +
                ", PMLYSpeed=" + PMLYSpeed +
                ", PMSpeed=" + PMSpeed +
                ", PMSpeedMOM=" + PMSpeedMOM +
                '}';
    }
}
