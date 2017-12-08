package com.wiceflow.json.fastjson.hipo;

import javax.persistence.*;

/**
 * Created by duxin on 2017/12/7.
 */
@Entity
@Table(name = "CrossTrafficList", schema = "public", catalog = "indexSystem")
public class CrossTrafficListEntity {
    private int id;
    private Double amSpeed;
    private String crossName;
    private Double pmSpeed;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "CrossName", nullable = true, length = 255)
    public String getCrossName() {
        return crossName;
    }

    public void setCrossName(String crossName) {
        this.crossName = crossName;
    }

    @Basic
    @Column(name = "PMSpeed", nullable = true, precision = 0)
    public Double getPmSpeed() {
        return pmSpeed;
    }

    public void setPmSpeed(Double pmSpeed) {
        this.pmSpeed = pmSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CrossTrafficListEntity that = (CrossTrafficListEntity) o;

        if (id != that.id) return false;
        if (amSpeed != null ? !amSpeed.equals(that.amSpeed) : that.amSpeed != null) return false;
        if (crossName != null ? !crossName.equals(that.crossName) : that.crossName != null) return false;
        if (pmSpeed != null ? !pmSpeed.equals(that.pmSpeed) : that.pmSpeed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (amSpeed != null ? amSpeed.hashCode() : 0);
        result = 31 * result + (crossName != null ? crossName.hashCode() : 0);
        result = 31 * result + (pmSpeed != null ? pmSpeed.hashCode() : 0);
        return result;
    }
}
