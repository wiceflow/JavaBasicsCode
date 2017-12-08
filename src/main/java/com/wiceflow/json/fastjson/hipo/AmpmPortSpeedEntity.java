package com.wiceflow.json.fastjson.hipo;

import javax.persistence.*;

/**
 * Created by duxin on 2017/12/7.
 */
@Entity
@Table(name = "AMPMPortSpeed", schema = "public", catalog = "indexSystem")
public class AmpmPortSpeedEntity {
    private int id;
    private Double amlwSpeed;
    private Double amlySpeed;
    private Double amSpeed;
    private Double amSpeedMom;
    private Double amSpeedYoy;
    private String districtName;
    private Double pmlwSpeed;
    private Double pmlySpeed;
    private Double pmSpeed;
    private Double pmSpeedMom;
    private Double pmSpeedYoy;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AMLWSpeed", nullable = true, precision = 0)
    public Double getAmlwSpeed() {
        return amlwSpeed;
    }

    public void setAmlwSpeed(Double amlwSpeed) {
        this.amlwSpeed = amlwSpeed;
    }

    @Basic
    @Column(name = "AMLYSpeed", nullable = true, precision = 0)
    public Double getAmlySpeed() {
        return amlySpeed;
    }

    public void setAmlySpeed(Double amlySpeed) {
        this.amlySpeed = amlySpeed;
    }

    @Basic
    @Column(name = "AMSpeed", nullable = true, precision = 0)
    public Double getAmSpeed() {
        return amSpeed;
    }

    public void setAmSpeed(Double amSpeed) {
        this.amSpeed = amSpeed;
    }

    @Basic
    @Column(name = "AMSpeedMOM", nullable = true, precision = 0)
    public Double getAmSpeedMom() {
        return amSpeedMom;
    }

    public void setAmSpeedMom(Double amSpeedMom) {
        this.amSpeedMom = amSpeedMom;
    }

    @Basic
    @Column(name = "AMSpeedYOY", nullable = true, precision = 0)
    public Double getAmSpeedYoy() {
        return amSpeedYoy;
    }

    public void setAmSpeedYoy(Double amSpeedYoy) {
        this.amSpeedYoy = amSpeedYoy;
    }

    @Basic
    @Column(name = "DistrictName", nullable = true, length = 255)
    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Basic
    @Column(name = "PMLWSpeed", nullable = true, precision = 0)
    public Double getPmlwSpeed() {
        return pmlwSpeed;
    }

    public void setPmlwSpeed(Double pmlwSpeed) {
        this.pmlwSpeed = pmlwSpeed;
    }

    @Basic
    @Column(name = "PMLYSpeed", nullable = true, precision = 0)
    public Double getPmlySpeed() {
        return pmlySpeed;
    }

    public void setPmlySpeed(Double pmlySpeed) {
        this.pmlySpeed = pmlySpeed;
    }

    @Basic
    @Column(name = "PMSpeed", nullable = true, precision = 0)
    public Double getPmSpeed() {
        return pmSpeed;
    }

    public void setPmSpeed(Double pmSpeed) {
        this.pmSpeed = pmSpeed;
    }

    @Basic
    @Column(name = "PMSpeedMOM", nullable = true, precision = 0)
    public Double getPmSpeedMom() {
        return pmSpeedMom;
    }

    public void setPmSpeedMom(Double pmSpeedMom) {
        this.pmSpeedMom = pmSpeedMom;
    }

    @Basic
    @Column(name = "PMSpeedYOY", nullable = true, precision = 0)
    public Double getPmSpeedYoy() {
        return pmSpeedYoy;
    }

    public void setPmSpeedYoy(Double pmSpeedYoy) {
        this.pmSpeedYoy = pmSpeedYoy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmpmPortSpeedEntity that = (AmpmPortSpeedEntity) o;

        if (id != that.id) return false;
        if (amlwSpeed != null ? !amlwSpeed.equals(that.amlwSpeed) : that.amlwSpeed != null) return false;
        if (amlySpeed != null ? !amlySpeed.equals(that.amlySpeed) : that.amlySpeed != null) return false;
        if (amSpeed != null ? !amSpeed.equals(that.amSpeed) : that.amSpeed != null) return false;
        if (amSpeedMom != null ? !amSpeedMom.equals(that.amSpeedMom) : that.amSpeedMom != null) return false;
        if (amSpeedYoy != null ? !amSpeedYoy.equals(that.amSpeedYoy) : that.amSpeedYoy != null) return false;
        if (districtName != null ? !districtName.equals(that.districtName) : that.districtName != null) return false;
        if (pmlwSpeed != null ? !pmlwSpeed.equals(that.pmlwSpeed) : that.pmlwSpeed != null) return false;
        if (pmlySpeed != null ? !pmlySpeed.equals(that.pmlySpeed) : that.pmlySpeed != null) return false;
        if (pmSpeed != null ? !pmSpeed.equals(that.pmSpeed) : that.pmSpeed != null) return false;
        if (pmSpeedMom != null ? !pmSpeedMom.equals(that.pmSpeedMom) : that.pmSpeedMom != null) return false;
        if (pmSpeedYoy != null ? !pmSpeedYoy.equals(that.pmSpeedYoy) : that.pmSpeedYoy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (amlwSpeed != null ? amlwSpeed.hashCode() : 0);
        result = 31 * result + (amlySpeed != null ? amlySpeed.hashCode() : 0);
        result = 31 * result + (amSpeed != null ? amSpeed.hashCode() : 0);
        result = 31 * result + (amSpeedMom != null ? amSpeedMom.hashCode() : 0);
        result = 31 * result + (amSpeedYoy != null ? amSpeedYoy.hashCode() : 0);
        result = 31 * result + (districtName != null ? districtName.hashCode() : 0);
        result = 31 * result + (pmlwSpeed != null ? pmlwSpeed.hashCode() : 0);
        result = 31 * result + (pmlySpeed != null ? pmlySpeed.hashCode() : 0);
        result = 31 * result + (pmSpeed != null ? pmSpeed.hashCode() : 0);
        result = 31 * result + (pmSpeedMom != null ? pmSpeedMom.hashCode() : 0);
        result = 31 * result + (pmSpeedYoy != null ? pmSpeedYoy.hashCode() : 0);
        return result;
    }
}
