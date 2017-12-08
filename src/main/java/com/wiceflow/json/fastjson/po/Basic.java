package com.wiceflow.json.fastjson.po;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by duxin on 2017/12/7.
 * 公司任务
 * 周报接口po
 */
@Entity
@Table(name = "basic", schema = "public", catalog = "indexSystem")
public class Basic {
    // 主键
    private int id;
    // 口岸早晚高峰 实体
    private AMPMPortSpeed AMPMPortSpeed;
    // 交通指数
    private double AMPeakWholeIndex;
    // 早高峰平均速度
    private double AMPeakWholeSpeed;
    // 主要二线关早高峰进关平均速度
    private double AMWholeCross;
    // 环比(+,-)
    private double AMWholeCrossMOM;
    // 同比(+,-)
    private double AMWholeCrossYOY;
    // 主要干道交通运行Set集合
    private Set<ArterialTrafficList> ArterialTrafficList = new HashSet<ArterialTrafficList>();
    // 关卡运行Set集合
    private Set<CrossTrafficList> CrossTrafficList = new HashSet<CrossTrafficList>();
    // 片区 + 枢纽Set集合
    private Set<DistrictTrafficList> DistrictTrafficList = new HashSet<DistrictTrafficList>();
    // 周报结束日期，int类型（例如：20151112）
    private int EndDate;
    // 环比
    private double MainRoadSpeedMOM;
    // 主要干道高峰时段平均速度
    private double MainRoadSpeedPeak;
    // 同比
    private double MainRoadSpeedYOY;
    // 交通指数
    private double PMPeakWholeIndex;
    // 晚高峰平均速度
    private double PMPeakWholeSpeed;
    // 主要二线关晚高峰出关平均速度
    private double PMWholeCross;
    // 环比(+,-)
    private double PMWholeCrossMOM;
    // 同比(+,-)
    private double PMWholeCrossYOY;
    // 停车收费城区交通运行Set集合
    private Set<ParkTrafficList> ParkTrafficList = new HashSet<ParkTrafficList>();
    // 口岸平均速度
    private double PortSpeed;
    // 环比
    private double PortSpeedMOM;
    // 同比
    private double PortSpeedYOY;
    // 城市交通运行Set集合
    private Set<SectTrafficList> SectTrafficList = new HashSet<SectTrafficList>();
    // 周报开始日期，Int类型（例如：20151106）
    private int StartDate;
    // 总期数
    private double TotalNo;
    // 期数
    private double WeeklyNo;
    // 全市路网高峰时段平均速度
    private double WholeSpeedPeak;
    // 环比(+,-)
    private double WholeSpeedPeakMOM;
    // 同比(+,-)
    private double WholeSpeedPeakYOY;
    // 整个交通情况Set集合
    private Set<WholeTrafficList> WholeTrafficList = new HashSet<WholeTrafficList>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name="id")
    public com.wiceflow.json.fastjson.po.AMPMPortSpeed getAMPMPortSpeed() {
        return AMPMPortSpeed;
    }
    public void setAMPMPortSpeed(com.wiceflow.json.fastjson.po.AMPMPortSpeed AMPMPortSpeed) {
        this.AMPMPortSpeed = AMPMPortSpeed;
    }

    @javax.persistence.Basic
    @Column(name = "\"AMPeakWholeIndex\"")
    public double getAMPeakWholeIndex() {
        return AMPeakWholeIndex;
    }
    public void setAMPeakWholeIndex(double AMPeakWholeIndex) {
        this.AMPeakWholeIndex = AMPeakWholeIndex;
    }

    @javax.persistence.Basic
    @Column(name = "\"AMPeakWholeSpeed\"")
    public double getAMPeakWholeSpeed() {
        return AMPeakWholeSpeed;
    }
    public void setAMPeakWholeSpeed(double AMPeakWholeSpeed) {
        this.AMPeakWholeSpeed = AMPeakWholeSpeed;
    }

    @javax.persistence.Basic
    @Column(name = "\"AMWholeCross\"")
    public double getAMWholeCross() {
        return AMWholeCross;
    }
    public void setAMWholeCross(double AMWholeCross) {
        this.AMWholeCross = AMWholeCross;
    }

    @javax.persistence.Basic
    @Column(name = "\"AMWholeCrossMOM\"")
    public double getAMWholeCrossMOM() {
        return AMWholeCrossMOM;
    }
    public void setAMWholeCrossMOM(double AMWholeCrossMOM) {
        this.AMWholeCrossMOM = AMWholeCrossMOM;
    }

    @javax.persistence.Basic
    @Column(name = "\"AMWholeCrossYOY\"")
    public double getAMWholeCrossYOY() {
        return AMWholeCrossYOY;
    }
    public void setAMWholeCrossYOY(double AMWholeCrossYOY) {
        this.AMWholeCrossYOY = AMWholeCrossYOY;
    }

    @javax.persistence.Basic
    @Column(name = "\"EndDate\"")
    public int getEndDate() {
        return EndDate;
    }
    public void setEndDate(int endDate) {
        EndDate = endDate;
    }

    @javax.persistence.Basic
    @Column(name = "\"MainRoadSpeedMOM\"")
    public double getMainRoadSpeedMOM() {
        return MainRoadSpeedMOM;
    }
    public void setMainRoadSpeedMOM(double mainRoadSpeedMOM) {
        MainRoadSpeedMOM = mainRoadSpeedMOM;
    }

    @javax.persistence.Basic
    @Column(name = "\"MainRoadSpeedPeak\"")
    public double getMainRoadSpeedPeak() {
        return MainRoadSpeedPeak;
    }
    public void setMainRoadSpeedPeak(double mainRoadSpeedPeak) {
        MainRoadSpeedPeak = mainRoadSpeedPeak;
    }

    @javax.persistence.Basic
    @Column(name = "\"MainRoadSpeedYOY\"")
    public double getMainRoadSpeedYOY() {
        return MainRoadSpeedYOY;
    }
    public void setMainRoadSpeedYOY(double mainRoadSpeedYOY) {
        MainRoadSpeedYOY = mainRoadSpeedYOY;
    }

    @javax.persistence.Basic
    @Column(name = "\"PMPeakWholeIndex\"")
    public double getPMPeakWholeIndex() {
        return PMPeakWholeIndex;
    }
    public void setPMPeakWholeIndex(double PMPeakWholeIndex) {
        this.PMPeakWholeIndex = PMPeakWholeIndex;
    }

    @javax.persistence.Basic
    @Column(name = "\"PMPeakWholeSpeed\"")
    public double getPMPeakWholeSpeed() {
        return PMPeakWholeSpeed;
    }
    public void setPMPeakWholeSpeed(double PMPeakWholeSpeed) {
        this.PMPeakWholeSpeed = PMPeakWholeSpeed;
    }

    @javax.persistence.Basic
    @Column(name = "\"PMWholeCross\"")
    public double getPMWholeCross() {
        return PMWholeCross;
    }
    public void setPMWholeCross(double PMWholeCross) {
        this.PMWholeCross = PMWholeCross;
    }

    @javax.persistence.Basic
    @Column(name = "\"PMWholeCrossMOM\"")
    public double getPMWholeCrossMOM() {
        return PMWholeCrossMOM;
    }
    public void setPMWholeCrossMOM(double PMWholeCrossMOM) {
        this.PMWholeCrossMOM = PMWholeCrossMOM;
    }

    @javax.persistence.Basic
    @Column(name = "\"PMWholeCrossYOY\"")
    public double getPMWholeCrossYOY() {
        return PMWholeCrossYOY;
    }
    public void setPMWholeCrossYOY(double PMWholeCrossYOY) {
        this.PMWholeCrossYOY = PMWholeCrossYOY;
    }

    @javax.persistence.Basic
    @Column(name = "\"PortSpeed\"")
    public double getPortSpeed() {
        return PortSpeed;
    }
    public void setPortSpeed(double portSpeed) {
        PortSpeed = portSpeed;
    }

    @javax.persistence.Basic
    @Column(name = "\"PortSpeedMOM\"")
    public double getPortSpeedMOM() {
        return PortSpeedMOM;
    }
    public void setPortSpeedMOM(double portSpeedMOM) {
        PortSpeedMOM = portSpeedMOM;
    }

    @javax.persistence.Basic
    @Column(name = "\"PortSpeedYOY\"")
    public double getPortSpeedYOY() {
        return PortSpeedYOY;
    }
    public void setPortSpeedYOY(double portSpeedYOY) {
        PortSpeedYOY = portSpeedYOY;
    }

    @javax.persistence.Basic
    @Column(name = "\"StartDate\"")
    public int getStartDate() {
        return StartDate;
    }
    public void setStartDate(int startDate) {
        StartDate = startDate;
    }

    @javax.persistence.Basic
    @Column(name = "\"TotalNo\"")
    public double getTotalNo() {
        return TotalNo;
    }
    public void setTotalNo(double totalNo) {
        TotalNo = totalNo;
    }

    @javax.persistence.Basic
    @Column(name = "\"WeeklyNo\"")
    public double getWeeklyNo() {
        return WeeklyNo;
    }
    public void setWeeklyNo(double weeklyNo) {
        WeeklyNo = weeklyNo;
    }

    @javax.persistence.Basic
    @Column(name = "\"WholeSpeedPeak\"")
    public double getWholeSpeedPeak() {
        return WholeSpeedPeak;
    }
    public void setWholeSpeedPeak(double wholeSpeedPeak) {
        WholeSpeedPeak = wholeSpeedPeak;
    }

    @javax.persistence.Basic
    @Column(name = "\"WholeSpeedPeakMOM\"")
    public double getWholeSpeedPeakMOM() {
        return WholeSpeedPeakMOM;
    }
    public void setWholeSpeedPeakMOM(double wholeSpeedPeakMOM) {
        WholeSpeedPeakMOM = wholeSpeedPeakMOM;
    }

    @javax.persistence.Basic
    @Column(name = "\"WholeSpeedPeakYOY\"")
    public double getWholeSpeedPeakYOY() {
        return WholeSpeedPeakYOY;
    }
    public void setWholeSpeedPeakYOY(double wholeSpeedPeakYOY) {
        WholeSpeedPeakYOY = wholeSpeedPeakYOY;
    }

    @OneToMany
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name = "\"pId\"")
    public Set<ArterialTrafficList> getArterialTrafficList() {
        return ArterialTrafficList;
    }
    public void setArterialTrafficList(Set<com.wiceflow.json.fastjson.po.ArterialTrafficList> arterialTrafficList) {
        ArterialTrafficList = arterialTrafficList;
    }

    @OneToMany
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name = "\"pId\"")
    public Set<com.wiceflow.json.fastjson.po.CrossTrafficList> getCrossTrafficList() {
        return CrossTrafficList;
    }
    public void setCrossTrafficList(Set<com.wiceflow.json.fastjson.po.CrossTrafficList> crossTrafficList) {
        CrossTrafficList = crossTrafficList;
    }

    @OneToMany
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name = "\"pId\"")
    public Set<com.wiceflow.json.fastjson.po.DistrictTrafficList> getDistrictTrafficList() {
        return DistrictTrafficList;
    }
    public void setDistrictTrafficList(Set<com.wiceflow.json.fastjson.po.DistrictTrafficList> districtTrafficList) {
        DistrictTrafficList = districtTrafficList;
    }

    @OneToMany
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name = "\"pId\"")
    public Set<com.wiceflow.json.fastjson.po.ParkTrafficList> getParkTrafficList() {
        return ParkTrafficList;
    }
    public void setParkTrafficList(Set<com.wiceflow.json.fastjson.po.ParkTrafficList> parkTrafficList) {
        ParkTrafficList = parkTrafficList;
    }

    @OneToMany
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name = "\"pId\"")
    public Set<com.wiceflow.json.fastjson.po.SectTrafficList> getSectTrafficList() {
        return SectTrafficList;
    }
    public void setSectTrafficList(Set<com.wiceflow.json.fastjson.po.SectTrafficList> sectTrafficList) {
        SectTrafficList = sectTrafficList;
    }

    @OneToMany
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name = "\"pId\"")
    public Set<com.wiceflow.json.fastjson.po.WholeTrafficList> getWholeTrafficList() {
        return WholeTrafficList;
    }
    public void setWholeTrafficList(Set<com.wiceflow.json.fastjson.po.WholeTrafficList> wholeTrafficList) {
        WholeTrafficList = wholeTrafficList;
    }

    @Override
    public String toString() {
        return "Basic{" +
                ", AMPMPortSpeed=" + AMPMPortSpeed +
                ", AMPeakWholeIndex=" + AMPeakWholeIndex +
                ", AMPeakWholeSpeed=" + AMPeakWholeSpeed +
                ", AMWholeCross=" + AMWholeCross +
                ", AMWholeCrossMOM=" + AMWholeCrossMOM +
                ", AMWholeCrossYOY=" + AMWholeCrossYOY +
                ", ArterialTrafficList=" + ArterialTrafficList +
                ", CrossTrafficList=" + CrossTrafficList +
                ", DistrictTrafficList=" + DistrictTrafficList +
                ", EndDate=" + EndDate +
                ", MainRoadSpeedMOM=" + MainRoadSpeedMOM +
                ", MainRoadSpeedPeak=" + MainRoadSpeedPeak +
                ", MainRoadSpeedYOY=" + MainRoadSpeedYOY +
                ", PMPeakWholeIndex=" + PMPeakWholeIndex +
                ", PMPeakWholeSpeed=" + PMPeakWholeSpeed +
                ", PMWholeCross=" + PMWholeCross +
                ", PMWholeCrossMOM=" + PMWholeCrossMOM +
                ", PMWholeCrossYOY=" + PMWholeCrossYOY +
                ", ParkTrafficList=" + ParkTrafficList +
                ", PortSpeed=" + PortSpeed +
                ", PortSpeedMOM=" + PortSpeedMOM +
                ", PortSpeedYOY=" + PortSpeedYOY +
                ", SectTrafficList=" + SectTrafficList +
                ", StartDate=" + StartDate +
                ", TotalNo=" + TotalNo +
                ", WeeklyNo=" + WeeklyNo +
                ", WholeSpeedPeak=" + WholeSpeedPeak +
                ", WholeSpeedPeakMOM=" + WholeSpeedPeakMOM +
                ", WholeSpeedPeakYOY=" + WholeSpeedPeakYOY +
                ", WholeTrafficList=" + WholeTrafficList +
                '}';
    }
}
