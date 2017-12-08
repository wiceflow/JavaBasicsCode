package com.wiceflow.json.fastjson.po;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Basic;

/**
 * Created by duxin on 2017/12/7.
 * AMPMPortSpeed - 口岸早/晚高峰
 */
@Entity
@Table(name = "\"AMPMPortSpeed\"", schema = "public", catalog = "indexSystem")
public class AMPMPortSpeed{
    // 主键
    private int id;
    // 外键
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

    private int pid;

    @Basic
    @Column(name = "pid")
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 一对多中是否不需要外键联系
     * @return
     */
    @Basic
    @Column(name = "\"AMLWSpeed\"")
    public double getAMLWSpeed()  {
        return AMLWSpeed;
    }
    public void setAMLWSpeed(double AMLWSpeed) {
        this.AMLWSpeed = AMLWSpeed;
    }

    @Basic
    @Column(name = "\"AMLYSpeed\"")
    public double getAMLYSpeed() {
        return AMLYSpeed;
    }
    public void setAMLYSpeed(double AMLYSpeed) {
        this.AMLYSpeed = AMLYSpeed;
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
    @Column(name = "\"AMSpeedMOM\"")
    public double getAMSpeedMOM() {
        return AMSpeedMOM;
    }
    public void setAMSpeedMOM(double AMSpeedMOM) {
        this.AMSpeedMOM = AMSpeedMOM;
    }

    @Basic
    @Column(name = "\"AMSpeedYOY\"")
    public double getAMSpeedYOY() {
        return AMSpeedYOY;
    }
    public void setAMSpeedYOY(double AMSpeedYOY) {
        this.AMSpeedYOY = AMSpeedYOY;
    }

    @Basic
    @Type(type = "string")
    @Column(name = "\"DistrictName\"")
    public String getDistrictName() {
        return DistrictName;
    }
    public void setDistrictName(String districtName) {
        DistrictName = districtName;
    }

    @Basic
    @Column(name = "\"PMLWSpeed\"")
    public double getPMLWSpeed() {
        return PMLWSpeed;
    }
    public void setPMLWSpeed(double PMLWSpeed) {
        this.PMLWSpeed = PMLWSpeed;
    }

    @Basic
    @Column(name = "\"PMLYSpeed\"")
    public double getPMLYSpeed() {
        return PMLYSpeed;
    }
    public void setPMLYSpeed(double PMLYSpeed) {
        this.PMLYSpeed = PMLYSpeed;
    }

    @Basic
    @Column(name = "\"PMSpeed\"")
    public double getPMSpeed() {
        return PMSpeed;
    }
    public void setPMSpeed(double PMSpeed) {
        this.PMSpeed = PMSpeed;
    }

    @Basic
    @Column(name = "\"PMSpeedMOM\"")
    public double getPMSpeedMOM() {
        return PMSpeedMOM;
    }
    public void setPMSpeedMOM(double PMSpeedMOM) {
        this.PMSpeedMOM = PMSpeedMOM;
    }

    @Basic
    @Column(name = "\"PMSpeedYOY\"")
    public double getPMSpeedYOY() {
        return PMSpeedYOY;
    }
    public void setPMSpeedYOY(double PMSpeedYOY) {
        this.PMSpeedYOY = PMSpeedYOY;
    }

    @Override
    public String toString() {
        return " \nAMPMPortSpeed{" +
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
