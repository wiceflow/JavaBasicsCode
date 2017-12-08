package com.wiceflow.json.fastjson.hipo;

import javax.persistence.*;

/**
 * Created by duxin on 2017/12/7.
 */
@Entity
@Table(name = "SectTrafficList", schema = "public", catalog = "indexSystem")
public class SectTrafficListEntity {
    private int id;
    private Double index;
    private Double lwSpeed;
    private String sectName;
    private Double speed;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Index", nullable = true, precision = 0)
    public Double getIndex() {
        return index;
    }

    public void setIndex(Double index) {
        this.index = index;
    }

    @Basic
    @Column(name = "LWSpeed", nullable = true, precision = 0)
    public Double getLwSpeed() {
        return lwSpeed;
    }

    public void setLwSpeed(Double lwSpeed) {
        this.lwSpeed = lwSpeed;
    }

    @Basic
    @Column(name = "SectName", nullable = true, length = 255)
    public String getSectName() {
        return sectName;
    }

    public void setSectName(String sectName) {
        this.sectName = sectName;
    }

    @Basic
    @Column(name = "Speed", nullable = true, precision = 0)
    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SectTrafficListEntity that = (SectTrafficListEntity) o;

        if (id != that.id) return false;
        if (index != null ? !index.equals(that.index) : that.index != null) return false;
        if (lwSpeed != null ? !lwSpeed.equals(that.lwSpeed) : that.lwSpeed != null) return false;
        if (sectName != null ? !sectName.equals(that.sectName) : that.sectName != null) return false;
        if (speed != null ? !speed.equals(that.speed) : that.speed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (index != null ? index.hashCode() : 0);
        result = 31 * result + (lwSpeed != null ? lwSpeed.hashCode() : 0);
        result = 31 * result + (sectName != null ? sectName.hashCode() : 0);
        result = 31 * result + (speed != null ? speed.hashCode() : 0);
        return result;
    }
}
