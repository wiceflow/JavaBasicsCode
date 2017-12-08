package com.wiceflow.json.fastjson.po;

/**
 * Created by duxin on 2017/12/7.
 * 片区 + 枢纽 可以extend AMPMPortSpeed
 */
public class DistrictTrafficList {

    // 主键
    private int id;
    // 外键
    private int pid;
    // 早高峰上周速度
    private double AMLWSpeed;
    // 早高峰同比周速度
    private double AMLYSpeed;
    // 早高峰速度
    private double AMSpeed;
    // 早高峰环比(+,-)
    private double AMSpeedMOM;
    // 早高峰同比(+,-)
    private double AMSpeedYOY;
    // 区域名称
    private String DistrictName;
    // 晚高峰上周速度
    private double PMLWSpeed;
    // 晚高峰同比周速度
    private double PMLYSpeed;
    // 晚高峰速度
    private double PMSpeed;
    // 晚高峰环比(+,-)
    private double PMSpeedMOM;
    // 晚高峰同比(+,-)
    private double PMSpeedYOY;

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

    public double getAMSpeedYOY() {
        return AMSpeedYOY;
    }

    public void setAMSpeedYOY(double AMSpeedYOY) {
        this.AMSpeedYOY = AMSpeedYOY;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        DistrictName = districtName;
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

    public double getPMSpeedYOY() {
        return PMSpeedYOY;
    }

    public void setPMSpeedYOY(double PMSpeedYOY) {
        this.PMSpeedYOY = PMSpeedYOY;
    }

    @Override
    public String toString() {
        return "\nDistrictTrafficList{" +
                " AMLWSpeed=" + AMLWSpeed +
                ", AMLYSpeed=" + AMLYSpeed +
                ", AMSpeed=" + AMSpeed +
                ", AMSpeedMOM=" + AMSpeedMOM +
                ", AMSpeedYOY=" + AMSpeedYOY +
                ", DistrictName='" + DistrictName + '\'' +
                ", PMLWSpeed=" + PMLWSpeed +
                ", PMLYSpeed=" + PMLYSpeed +
                ", PMSpeed=" + PMSpeed +
                ", PMSpeedMOM=" + PMSpeedMOM +
                ", PMSpeedYOY=" + PMSpeedYOY +
                '}';
    }
}
