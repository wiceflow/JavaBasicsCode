package com.wiceflow.json.fastjson.po;

import javax.persistence.*;
import javax.persistence.Basic;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by BF on 2017/12/13.
 * 各分区交通运行表 area
 */
@Entity
@Table(name = "\"TrafficInAll\"", schema = "public", catalog = "indexSystem")
public class TrafficInAll {
    private int id;
    private String dministrativeRegion;
    private double lastWeek;
    private double thisWeek;
    private String chainRatio;
    private double index;
    private int pId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "\"DministrativeRegion\"", nullable = false, length = 50)
    public String getDministrativeRegion() {
        return dministrativeRegion;
    }
    public void setDministrativeRegion(String dministrativeRegion) {
        this.dministrativeRegion = dministrativeRegion;
    }

    @Basic
    @Column(name = "\"LastWeek\"", nullable = false, precision = 0)
    public double getLastWeek() {
        return lastWeek;
    }
    public void setLastWeek(double lastWeek) {
        this.lastWeek = lastWeek;
    }

    @Basic
    @Column(name = "\"ThisWeek\"", nullable = false, precision = 0)
    public double getThisWeek() {
        return thisWeek;
    }
    public void setThisWeek(double thisWeek) {
        this.thisWeek = thisWeek;
    }

    @Basic
    @Column(name = "\"ChainRatio\"", nullable = false)
    public String getChainRatio() {
        return chainRatio;
    }
    public void setChainRatio(String chainRatio) {
        this.chainRatio = chainRatio;
    }

    @Basic
    @Column(name = "\"Index\"", nullable = false, precision = 0)
    public double getIndex() {
        return index;
    }
    public void setIndex(double index) {
        this.index = index;
    }

    @Basic
    @Column(name = "\"pId\"", nullable = false)
    public int getpId() {
        return pId;
    }
    public void setpId(int pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return "\nTrafficInAll{" +
                "id=" + id +
                ", dministrativeRegion='" + dministrativeRegion + '\'' +
                ", laskWeek=" + lastWeek +
                ", thisWeek=" + thisWeek +
                ", chainRatio=" + chainRatio +
                ", index=" + index +
                ", pId=" + pId +
                '}';
    }
}
