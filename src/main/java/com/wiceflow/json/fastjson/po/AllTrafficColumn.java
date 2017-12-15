package com.wiceflow.json.fastjson.po;

import javax.persistence.*;

/**
 * Created by BF on 2017/12/14.
 * 对应JSON数据中的 all 字段 手动封装成对象
 */
@Entity
@Table(name = "\"AllTrafficColumn\"", schema = "public", catalog = "indexSystem")
public class AllTrafficColumn {
    private int id;
    /**
     * 周次
     */
    private String week;
    private int startDate;
    private double fullTimeSpeed;
    private double earlySpeed;
    private double lateSpeed;
    private double peakSpeed;
    private double centralCityPeakSpeed;
    private double lastYearPeakSpeed;
    private double lastYearCentralCityPeakSpeed;
    private String cityPeakSpeedChain;
    private String cityPeakSpeedYoy;
    private String centralCityPeakSpeedChain;
    private String centralCityPeakSpeedYoy;
    private int tId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取周次
     * @return
     */
    @Basic
    @Column(name = "\"Week\"", nullable = false, length = 50)
    public String getWeek() {
        return week;
    }
    public void setWeek(String week) {
        this.week = week;
    }

    @Basic
    @Column(name = "\"StartDate\"", nullable = false)
    public int getStartDate() {
        return startDate;
    }
    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "\"FullTimeSpeed\"", nullable = false, precision = 0)
    public double getFullTimeSpeed() {
        return fullTimeSpeed;
    }
    public void setFullTimeSpeed(double fullTimeSpeed) {
        this.fullTimeSpeed = fullTimeSpeed;
    }

    @Basic
    @Column(name = "\"EarlySpeed\"", nullable = false, precision = 0)
    public double getEarlySpeed() {
        return earlySpeed;
    }
    public void setEarlySpeed(double earlySpeed) {
        this.earlySpeed = earlySpeed;
    }

    @Basic
    @Column(name = "\"LateSpeed\"", nullable = false, precision = 0)
    public double getLateSpeed() {
        return lateSpeed;
    }
    public void setLateSpeed(double lateSpeed) {
        this.lateSpeed = lateSpeed;
    }

    @Basic
    @Column(name = "\"PeakSpeed\"", nullable = false, precision = 0)
    public double getPeakSpeed() {
        return peakSpeed;
    }
    public void setPeakSpeed(double peakSpeed) {
        this.peakSpeed = peakSpeed;
    }

    @Basic
    @Column(name = "\"CentralCityPeakSpeed\"", nullable = false, precision = 0)
    public double getCentralCityPeakSpeed() {
        return centralCityPeakSpeed;
    }
    public void setCentralCityPeakSpeed(double centralCityPeakSpeed) {
        this.centralCityPeakSpeed = centralCityPeakSpeed;
    }

    @Basic
    @Column(name = "\"LastYearPeakSpeed\"", nullable = true, precision = 0)
    public double getLastYearPeakSpeed() {
        return lastYearPeakSpeed;
    }
    public void setLastYearPeakSpeed(double lastYearPeakSpeed) {
        this.lastYearPeakSpeed = lastYearPeakSpeed;
    }

    @Basic
    @Column(name = "\"LastYearCentralCityPeakSpeed\"", nullable = true, precision = 0)
    public double getLastYearCentralCityPeakSpeed() {
        return lastYearCentralCityPeakSpeed;
    }
    public void setLastYearCentralCityPeakSpeed(double lastYearCentralCityPeakSpeed) {
        this.lastYearCentralCityPeakSpeed = lastYearCentralCityPeakSpeed;
    }

    @Basic
    @Column(name = "\"CityPeakSpeedChain\"", nullable = false, length = 50)
    public String getCityPeakSpeedChain() {
        return cityPeakSpeedChain;
    }
    public void setCityPeakSpeedChain(String cityPeakSpeedChain) {
        this.cityPeakSpeedChain = cityPeakSpeedChain;
    }

    @Basic
    @Column(name = "\"CityPeakSpeedYOY\"", nullable = false, length = 50)
    public String getCityPeakSpeedYoy() {
        return cityPeakSpeedYoy;
    }
    public void setCityPeakSpeedYoy(String cityPeakSpeedYoy) {
        this.cityPeakSpeedYoy = cityPeakSpeedYoy;
    }

    @Basic
    @Column(name = "\"CentralCityPeakSpeedChain\"", nullable = false, length = 50)
    public String getCentralCityPeakSpeedChain() {
        return centralCityPeakSpeedChain;
    }
    public void setCentralCityPeakSpeedChain(String centralCityPeakSpeedChain) {
        this.centralCityPeakSpeedChain = centralCityPeakSpeedChain;
    }

    @Basic
    @Column(name = "\"CentralCityPeakSpeedYOY\"", nullable = false, length = 50)
    public String getCentralCityPeakSpeedYoy() {
        return centralCityPeakSpeedYoy;
    }
    public void setCentralCityPeakSpeedYoy(String centralCityPeakSpeedYoy) {
        this.centralCityPeakSpeedYoy = centralCityPeakSpeedYoy;
    }

    @Basic
    @Column(name = "\"tId\"", nullable = false)
    public int gettId() {
        return tId;
    }
    public void settId(int tId) {
        this.tId = tId;
    }

    @Override
    public String toString() {
        return "\nAllTrafficColumn{" +
                "id=" + id +
                ", week='" + week + '\'' +
                ", startDate=" + startDate +
                ", fullTimeSpeed=" + fullTimeSpeed +
                ", earlySpeed=" + earlySpeed +
                ", lateSpeed=" + lateSpeed +
                ", peakSpeed=" + peakSpeed +
                ", centralCityPeakSpeed=" + centralCityPeakSpeed +
                ", lastYearPeakSpeed=" + lastYearPeakSpeed +
                ", lastYearCentralCityPeakSpeed=" + lastYearCentralCityPeakSpeed +
                ", cityPeakSpeedChain='" + cityPeakSpeedChain + '\'' +
                ", cityPeakSpeedYoy='" + cityPeakSpeedYoy + '\'' +
                ", centralCityPeakSpeedChain='" + centralCityPeakSpeedChain + '\'' +
                ", centralCityPeakSpeedYoy='" + centralCityPeakSpeedYoy + '\'' +
                ", tId=" + tId +
                '}';
    }
}
