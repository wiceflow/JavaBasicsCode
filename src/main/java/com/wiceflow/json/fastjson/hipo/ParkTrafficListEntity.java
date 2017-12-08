package com.wiceflow.json.fastjson.hipo;

import javax.persistence.*;

/**
 * Created by duxin on 2017/12/7.
 */
@Entity
@Table(name = "ParkTrafficList", schema = "public", catalog = "indexSystem")
public class ParkTrafficListEntity {
    private int id;
    private String parkName;
    private Double speed;
    private Double speedBefore;
    private Double speedMom;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ParkName", nullable = true, length = 255)
    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    @Basic
    @Column(name = "Speed", nullable = true, precision = 0)
    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    @Basic
    @Column(name = "SpeedBefore", nullable = true, precision = 0)
    public Double getSpeedBefore() {
        return speedBefore;
    }

    public void setSpeedBefore(Double speedBefore) {
        this.speedBefore = speedBefore;
    }

    @Basic
    @Column(name = "SpeedMOM", nullable = true, precision = 0)
    public Double getSpeedMom() {
        return speedMom;
    }

    public void setSpeedMom(Double speedMom) {
        this.speedMom = speedMom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkTrafficListEntity that = (ParkTrafficListEntity) o;

        if (id != that.id) return false;
        if (parkName != null ? !parkName.equals(that.parkName) : that.parkName != null) return false;
        if (speed != null ? !speed.equals(that.speed) : that.speed != null) return false;
        if (speedBefore != null ? !speedBefore.equals(that.speedBefore) : that.speedBefore != null) return false;
        if (speedMom != null ? !speedMom.equals(that.speedMom) : that.speedMom != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (parkName != null ? parkName.hashCode() : 0);
        result = 31 * result + (speed != null ? speed.hashCode() : 0);
        result = 31 * result + (speedBefore != null ? speedBefore.hashCode() : 0);
        result = 31 * result + (speedMom != null ? speedMom.hashCode() : 0);
        return result;
    }
}
