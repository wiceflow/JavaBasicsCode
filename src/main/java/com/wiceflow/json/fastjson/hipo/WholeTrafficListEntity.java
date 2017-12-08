package com.wiceflow.json.fastjson.hipo;

import javax.persistence.*;

/**
 * Created by duxin on 2017/12/7.
 */
@Entity
@Table(name = "WholeTrafficList", schema = "public", catalog = "indexSystem")
public class WholeTrafficListEntity {
    private int id;
    private String seDate;
    private Double speed;
    private Double speedPeak;
    private Double weekNo;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SEDate", nullable = true, length = 255)
    public String getSeDate() {
        return seDate;
    }

    public void setSeDate(String seDate) {
        this.seDate = seDate;
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
    @Column(name = "SpeedPeak", nullable = true, precision = 0)
    public Double getSpeedPeak() {
        return speedPeak;
    }

    public void setSpeedPeak(Double speedPeak) {
        this.speedPeak = speedPeak;
    }

    @Basic
    @Column(name = "WeekNo", nullable = true, precision = 0)
    public Double getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(Double weekNo) {
        this.weekNo = weekNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WholeTrafficListEntity that = (WholeTrafficListEntity) o;

        if (id != that.id) return false;
        if (seDate != null ? !seDate.equals(that.seDate) : that.seDate != null) return false;
        if (speed != null ? !speed.equals(that.speed) : that.speed != null) return false;
        if (speedPeak != null ? !speedPeak.equals(that.speedPeak) : that.speedPeak != null) return false;
        if (weekNo != null ? !weekNo.equals(that.weekNo) : that.weekNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (seDate != null ? seDate.hashCode() : 0);
        result = 31 * result + (speed != null ? speed.hashCode() : 0);
        result = 31 * result + (speedPeak != null ? speedPeak.hashCode() : 0);
        result = 31 * result + (weekNo != null ? weekNo.hashCode() : 0);
        return result;
    }
}
