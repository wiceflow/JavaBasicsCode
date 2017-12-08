package com.wiceflow.json.fastjson.hipo;

import javax.persistence.*;

/**
 * Created by duxin on 2017/12/7.
 */
@Entity
@Table(name = "basic", schema = "public", catalog = "indexSystem")
public class BasicEntity {
    private int id;
    private Double amPeakWholeSpeed;
    private Double amPeakWholeIndex;
    private Double amWholeCross;
    private Double amWholeCrossMom;
    private Double amWholeCrossYoy;
    private Integer endDate;
    private Double mainRoadSpeedMom;
    private Double mainRoadSpeedPeak;
    private Double mainRoadSpeedYoy;
    private Double pmPeakWholeIndex;
    private Double pmPeakWholeSpeed;
    private Double pmWholeCross;
    private Double pmWholeCrossMom;
    private Double pmWholeCrossYoy;
    private Double portSpeed;
    private Double portSpeedMom;
    private Double portSpeedYoy;
    private Integer startDate;
    private Double totalNo;
    private Double weeklyNo;
    private Double wholeSpeedPeak;
    private Double wholeSpeedPeakMom;
    private Double wholeSpeedPeakYoy;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AMPeakWholeSpeed", nullable = true, precision = 0)
    public Double getAmPeakWholeSpeed() {
        return amPeakWholeSpeed;
    }

    public void setAmPeakWholeSpeed(Double amPeakWholeSpeed) {
        this.amPeakWholeSpeed = amPeakWholeSpeed;
    }

    @Basic
    @Column(name = "AMPeakWholeIndex", nullable = true, precision = 0)
    public Double getAmPeakWholeIndex() {
        return amPeakWholeIndex;
    }

    public void setAmPeakWholeIndex(Double amPeakWholeIndex) {
        this.amPeakWholeIndex = amPeakWholeIndex;
    }

    @Basic
    @Column(name = "AMWholeCross", nullable = true, precision = 0)
    public Double getAmWholeCross() {
        return amWholeCross;
    }

    public void setAmWholeCross(Double amWholeCross) {
        this.amWholeCross = amWholeCross;
    }

    @Basic
    @Column(name = "AMWholeCrossMOM", nullable = true, precision = 0)
    public Double getAmWholeCrossMom() {
        return amWholeCrossMom;
    }

    public void setAmWholeCrossMom(Double amWholeCrossMom) {
        this.amWholeCrossMom = amWholeCrossMom;
    }

    @Basic
    @Column(name = "AMWholeCrossYOY", nullable = true, precision = 0)
    public Double getAmWholeCrossYoy() {
        return amWholeCrossYoy;
    }

    public void setAmWholeCrossYoy(Double amWholeCrossYoy) {
        this.amWholeCrossYoy = amWholeCrossYoy;
    }

    @Basic
    @Column(name = "EndDate", nullable = true)
    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "MainRoadSpeedMOM", nullable = true, precision = 0)
    public Double getMainRoadSpeedMom() {
        return mainRoadSpeedMom;
    }

    public void setMainRoadSpeedMom(Double mainRoadSpeedMom) {
        this.mainRoadSpeedMom = mainRoadSpeedMom;
    }

    @Basic
    @Column(name = "MainRoadSpeedPeak", nullable = true, precision = 0)
    public Double getMainRoadSpeedPeak() {
        return mainRoadSpeedPeak;
    }

    public void setMainRoadSpeedPeak(Double mainRoadSpeedPeak) {
        this.mainRoadSpeedPeak = mainRoadSpeedPeak;
    }

    @Basic
    @Column(name = "MainRoadSpeedYOY", nullable = true, precision = 0)
    public Double getMainRoadSpeedYoy() {
        return mainRoadSpeedYoy;
    }

    public void setMainRoadSpeedYoy(Double mainRoadSpeedYoy) {
        this.mainRoadSpeedYoy = mainRoadSpeedYoy;
    }

    @Basic
    @Column(name = "PMPeakWholeIndex", nullable = true, precision = 0)
    public Double getPmPeakWholeIndex() {
        return pmPeakWholeIndex;
    }

    public void setPmPeakWholeIndex(Double pmPeakWholeIndex) {
        this.pmPeakWholeIndex = pmPeakWholeIndex;
    }

    @Basic
    @Column(name = "PMPeakWholeSpeed", nullable = true, precision = 0)
    public Double getPmPeakWholeSpeed() {
        return pmPeakWholeSpeed;
    }

    public void setPmPeakWholeSpeed(Double pmPeakWholeSpeed) {
        this.pmPeakWholeSpeed = pmPeakWholeSpeed;
    }

    @Basic
    @Column(name = "PMWholeCross", nullable = true, precision = 0)
    public Double getPmWholeCross() {
        return pmWholeCross;
    }

    public void setPmWholeCross(Double pmWholeCross) {
        this.pmWholeCross = pmWholeCross;
    }

    @Basic
    @Column(name = "PMWholeCrossMOM", nullable = true, precision = 0)
    public Double getPmWholeCrossMom() {
        return pmWholeCrossMom;
    }

    public void setPmWholeCrossMom(Double pmWholeCrossMom) {
        this.pmWholeCrossMom = pmWholeCrossMom;
    }

    @Basic
    @Column(name = "PMWholeCrossYOY", nullable = true, precision = 0)
    public Double getPmWholeCrossYoy() {
        return pmWholeCrossYoy;
    }

    public void setPmWholeCrossYoy(Double pmWholeCrossYoy) {
        this.pmWholeCrossYoy = pmWholeCrossYoy;
    }

    @Basic
    @Column(name = "PortSpeed", nullable = true, precision = 0)
    public Double getPortSpeed() {
        return portSpeed;
    }

    public void setPortSpeed(Double portSpeed) {
        this.portSpeed = portSpeed;
    }

    @Basic
    @Column(name = "PortSpeedMOM", nullable = true, precision = 0)
    public Double getPortSpeedMom() {
        return portSpeedMom;
    }

    public void setPortSpeedMom(Double portSpeedMom) {
        this.portSpeedMom = portSpeedMom;
    }

    @Basic
    @Column(name = "PortSpeedYOY", nullable = true, precision = 0)
    public Double getPortSpeedYoy() {
        return portSpeedYoy;
    }

    public void setPortSpeedYoy(Double portSpeedYoy) {
        this.portSpeedYoy = portSpeedYoy;
    }

    @Basic
    @Column(name = "StartDate", nullable = true)
    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "TotalNo", nullable = true, precision = 0)
    public Double getTotalNo() {
        return totalNo;
    }

    public void setTotalNo(Double totalNo) {
        this.totalNo = totalNo;
    }

    @Basic
    @Column(name = "WeeklyNo", nullable = true, precision = 0)
    public Double getWeeklyNo() {
        return weeklyNo;
    }

    public void setWeeklyNo(Double weeklyNo) {
        this.weeklyNo = weeklyNo;
    }

    @Basic
    @Column(name = "WholeSpeedPeak", nullable = true, precision = 0)
    public Double getWholeSpeedPeak() {
        return wholeSpeedPeak;
    }

    public void setWholeSpeedPeak(Double wholeSpeedPeak) {
        this.wholeSpeedPeak = wholeSpeedPeak;
    }

    @Basic
    @Column(name = "WholeSpeedPeakMOM", nullable = true, precision = 0)
    public Double getWholeSpeedPeakMom() {
        return wholeSpeedPeakMom;
    }

    public void setWholeSpeedPeakMom(Double wholeSpeedPeakMom) {
        this.wholeSpeedPeakMom = wholeSpeedPeakMom;
    }

    @Basic
    @Column(name = "WholeSpeedPeakYOY", nullable = true, precision = 0)
    public Double getWholeSpeedPeakYoy() {
        return wholeSpeedPeakYoy;
    }

    public void setWholeSpeedPeakYoy(Double wholeSpeedPeakYoy) {
        this.wholeSpeedPeakYoy = wholeSpeedPeakYoy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicEntity that = (BasicEntity) o;

        if (id != that.id) return false;
        if (amPeakWholeSpeed != null ? !amPeakWholeSpeed.equals(that.amPeakWholeSpeed) : that.amPeakWholeSpeed != null)
            return false;
        if (amPeakWholeIndex != null ? !amPeakWholeIndex.equals(that.amPeakWholeIndex) : that.amPeakWholeIndex != null)
            return false;
        if (amWholeCross != null ? !amWholeCross.equals(that.amWholeCross) : that.amWholeCross != null) return false;
        if (amWholeCrossMom != null ? !amWholeCrossMom.equals(that.amWholeCrossMom) : that.amWholeCrossMom != null)
            return false;
        if (amWholeCrossYoy != null ? !amWholeCrossYoy.equals(that.amWholeCrossYoy) : that.amWholeCrossYoy != null)
            return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (mainRoadSpeedMom != null ? !mainRoadSpeedMom.equals(that.mainRoadSpeedMom) : that.mainRoadSpeedMom != null)
            return false;
        if (mainRoadSpeedPeak != null ? !mainRoadSpeedPeak.equals(that.mainRoadSpeedPeak) : that.mainRoadSpeedPeak != null)
            return false;
        if (mainRoadSpeedYoy != null ? !mainRoadSpeedYoy.equals(that.mainRoadSpeedYoy) : that.mainRoadSpeedYoy != null)
            return false;
        if (pmPeakWholeIndex != null ? !pmPeakWholeIndex.equals(that.pmPeakWholeIndex) : that.pmPeakWholeIndex != null)
            return false;
        if (pmPeakWholeSpeed != null ? !pmPeakWholeSpeed.equals(that.pmPeakWholeSpeed) : that.pmPeakWholeSpeed != null)
            return false;
        if (pmWholeCross != null ? !pmWholeCross.equals(that.pmWholeCross) : that.pmWholeCross != null) return false;
        if (pmWholeCrossMom != null ? !pmWholeCrossMom.equals(that.pmWholeCrossMom) : that.pmWholeCrossMom != null)
            return false;
        if (pmWholeCrossYoy != null ? !pmWholeCrossYoy.equals(that.pmWholeCrossYoy) : that.pmWholeCrossYoy != null)
            return false;
        if (portSpeed != null ? !portSpeed.equals(that.portSpeed) : that.portSpeed != null) return false;
        if (portSpeedMom != null ? !portSpeedMom.equals(that.portSpeedMom) : that.portSpeedMom != null) return false;
        if (portSpeedYoy != null ? !portSpeedYoy.equals(that.portSpeedYoy) : that.portSpeedYoy != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (totalNo != null ? !totalNo.equals(that.totalNo) : that.totalNo != null) return false;
        if (weeklyNo != null ? !weeklyNo.equals(that.weeklyNo) : that.weeklyNo != null) return false;
        if (wholeSpeedPeak != null ? !wholeSpeedPeak.equals(that.wholeSpeedPeak) : that.wholeSpeedPeak != null)
            return false;
        if (wholeSpeedPeakMom != null ? !wholeSpeedPeakMom.equals(that.wholeSpeedPeakMom) : that.wholeSpeedPeakMom != null)
            return false;
        if (wholeSpeedPeakYoy != null ? !wholeSpeedPeakYoy.equals(that.wholeSpeedPeakYoy) : that.wholeSpeedPeakYoy != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (amPeakWholeSpeed != null ? amPeakWholeSpeed.hashCode() : 0);
        result = 31 * result + (amPeakWholeIndex != null ? amPeakWholeIndex.hashCode() : 0);
        result = 31 * result + (amWholeCross != null ? amWholeCross.hashCode() : 0);
        result = 31 * result + (amWholeCrossMom != null ? amWholeCrossMom.hashCode() : 0);
        result = 31 * result + (amWholeCrossYoy != null ? amWholeCrossYoy.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (mainRoadSpeedMom != null ? mainRoadSpeedMom.hashCode() : 0);
        result = 31 * result + (mainRoadSpeedPeak != null ? mainRoadSpeedPeak.hashCode() : 0);
        result = 31 * result + (mainRoadSpeedYoy != null ? mainRoadSpeedYoy.hashCode() : 0);
        result = 31 * result + (pmPeakWholeIndex != null ? pmPeakWholeIndex.hashCode() : 0);
        result = 31 * result + (pmPeakWholeSpeed != null ? pmPeakWholeSpeed.hashCode() : 0);
        result = 31 * result + (pmWholeCross != null ? pmWholeCross.hashCode() : 0);
        result = 31 * result + (pmWholeCrossMom != null ? pmWholeCrossMom.hashCode() : 0);
        result = 31 * result + (pmWholeCrossYoy != null ? pmWholeCrossYoy.hashCode() : 0);
        result = 31 * result + (portSpeed != null ? portSpeed.hashCode() : 0);
        result = 31 * result + (portSpeedMom != null ? portSpeedMom.hashCode() : 0);
        result = 31 * result + (portSpeedYoy != null ? portSpeedYoy.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (totalNo != null ? totalNo.hashCode() : 0);
        result = 31 * result + (weeklyNo != null ? weeklyNo.hashCode() : 0);
        result = 31 * result + (wholeSpeedPeak != null ? wholeSpeedPeak.hashCode() : 0);
        result = 31 * result + (wholeSpeedPeakMom != null ? wholeSpeedPeakMom.hashCode() : 0);
        result = 31 * result + (wholeSpeedPeakYoy != null ? wholeSpeedPeakYoy.hashCode() : 0);
        return result;
    }
}
