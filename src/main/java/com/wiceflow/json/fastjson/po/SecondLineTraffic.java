package com.wiceflow.json.fastjson.po;

import javax.persistence.*;
import javax.persistence.Basic;
import javax.persistence.Table;

/**
 * Created by BF on 2017/12/13.
 * 二线关交通运行表
 * 分早晚峰，其中AM为早峰,PM为晚峰'
 */
@Entity
@Table(name = "\"SecondLineTraffic\"", schema = "public", catalog = "indexSystem")
public class SecondLineTraffic {
    private String pass;
    private int id;
    private double amSameAsLastYear;
    private double amLastWeek;
    private double amWeek;
    private String amOperatingStatus;
    private double pmSameAsLastYear;
    private double pmLastWeek;
    private double pmWeek;
    private String pmSpeedMom;
    private String pmOperatingStatus;
    private int pId;
    private String amSpeedMom;

    @Basic
    @Column(name = "\"Pass\"", nullable = false, length = 50)
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

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
    @Column(name = "\"AMSameAsLastYear\"", nullable = false, precision = 0)
    public double getAmSameAsLastYear() {
        return amSameAsLastYear;
    }
    public void setAmSameAsLastYear(double amSameAsLastYear) {
        this.amSameAsLastYear = amSameAsLastYear;
    }

    @Basic
    @Column(name = "\"AMLastWeek\"", nullable = false, precision = 0)
    public double getAmLastWeek() {
        return amLastWeek;
    }
    public void setAmLastWeek(double amLastWeek) {
        this.amLastWeek = amLastWeek;
    }

    @Basic
    @Column(name = "\"AMWeek\"", nullable = false, precision = 0)
    public double getAmWeek() {
        return amWeek;
    }
    public void setAmWeek(double amWeek) {
        this.amWeek = amWeek;
    }

    @Basic
    @Column(name = "\"AMOperatingStatus\"", nullable = false, length = 70)
    public String getAmOperatingStatus() {
        return amOperatingStatus;
    }
    public void setAmOperatingStatus(String amOperatingStatus) {
        this.amOperatingStatus = amOperatingStatus;
    }

    @Basic
    @Column(name = "\"PMSameAsLastYear\"", nullable = false, precision = 0)
    public double getPmSameAsLastYear() {
        return pmSameAsLastYear;
    }
    public void setPmSameAsLastYear(double pmSameAsLastYear) {
        this.pmSameAsLastYear = pmSameAsLastYear;
    }

    @Basic
    @Column(name = "\"PMLastWeek\"", nullable = false, precision = 0)
    public double getPmLastWeek() {
        return pmLastWeek;
    }
    public void setPmLastWeek(double pmLastWeek) {
        this.pmLastWeek = pmLastWeek;
    }

    @Basic
    @Column(name = "\"PMWeek\"", nullable = false, precision = 0)
    public double getPmWeek() {
        return pmWeek;
    }
    public void setPmWeek(double pmWeek) {
        this.pmWeek = pmWeek;
    }

    @Basic
    @Column(name = "\"PMSpeedMOM\"", nullable = false, length = 50)
    public String getPmSpeedMom() {
        return pmSpeedMom;
    }
    public void setPmSpeedMom(String pmSpeedMom) {
        this.pmSpeedMom = pmSpeedMom;
    }

    @Basic
    @Column(name = "\"PMOperatingStatus\"", nullable = false, length = 70)
    public String getPmOperatingStatus() {
        return pmOperatingStatus;
    }
    public void setPmOperatingStatus(String pmOperatingStatus) {
        this.pmOperatingStatus = pmOperatingStatus;
    }

    @Basic
    @Column(name = "\"pId\"", nullable = false)
    public int getpId() {
        return pId;
    }
    public void setpId(int pId) {
        this.pId = pId;
    }

    @Basic
    @Column(name = "\"AMSpeedMOM\"", nullable = false, length = 50)
    public String getAmSpeedMom() {
        return amSpeedMom;
    }
    public void setAmSpeedMom(String amSpeedMom) {
        this.amSpeedMom = amSpeedMom;
    }

    @Override
    public String toString() {
        return "\nSecondLineTraffic{" +
                "pass='" + pass + '\'' +
                ", id=" + id +
                ", amSameAsLastYear=" + amSameAsLastYear +
                ", amLastWeek=" + amLastWeek +
                ", amWeek=" + amWeek +
                ", amOperatingStatus='" + amOperatingStatus + '\'' +
                ", pmSameAsLastYear=" + pmSameAsLastYear +
                ", pmLastWeek=" + pmLastWeek +
                ", pmWeek=" + pmWeek +
                ", pmSpeedMom='" + pmSpeedMom + '\'' +
                ", pmOperatingStatus='" + pmOperatingStatus + '\'' +
                ", pId=" + pId +
                ", amSpeedMom='" + amSpeedMom + '\'' +
                '}';
    }
}
