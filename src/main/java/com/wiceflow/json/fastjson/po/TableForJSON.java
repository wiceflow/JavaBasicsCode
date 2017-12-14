package com.wiceflow.json.fastjson.po;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by BF on 2017/12/13.
 * table 字段基础表
 */
@Entity
@Table(name = "\"Table\"", schema = "public", catalog = "indexSystem")
public class TableForJSON {
    private int id;
    private String period;
    private Set<TrafficInAll> trafficInAll = new HashSet<TrafficInAll>();
    private Set<SecondLineTraffic> secondLineTraffic = new HashSet<SecondLineTraffic>();
    private Set<AllTrafficColumn> allTrafficColumns = new HashSet<AllTrafficColumn>();
    private AllTrafficColumn allTrafficColumn;
    private int bId;

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
    @Column(name = "period", nullable = false, length = 50)
    public String getPeriod() {
        return period;
    }
    public void setPeriod(String period) {
        this.period = period;
    }

    @OneToMany
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name = "\"pId\"")
    public Set<TrafficInAll> getTrafficInAll() {
        return trafficInAll;
    }
    public void setTrafficInAll(Set<TrafficInAll> trafficInAll) {
        this.trafficInAll = trafficInAll;
    }

    @OneToMany
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name = "\"pId\"")
    public Set<SecondLineTraffic> getSecondLineTraffic() {
        return secondLineTraffic;
    }
    public void setSecondLineTraffic(Set<SecondLineTraffic> secondLineTraffic) {
        this.secondLineTraffic = secondLineTraffic;
    }
    @OneToMany
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name = "\"tId\"")
    public Set<AllTrafficColumn> getAllTrafficColumns() {
        return allTrafficColumns;
    }
    public void setAllTrafficColumns(Set<AllTrafficColumn> allTrafficColumns) {
        this.allTrafficColumns = allTrafficColumns;
    }

    @Basic
    @Column(name = "\"bId\"", nullable = false)
    public int getbId() {
        return bId;
    }
    public void setbId(int bId) {
        this.bId = bId;
    }

    @Transient
    public AllTrafficColumn getAllTrafficColumn() {
        return allTrafficColumns.iterator().next();
    }
    public void setAllTrafficColumn(AllTrafficColumn allTrafficColumn) {
        this.allTrafficColumn = allTrafficColumn;
        allTrafficColumns.add(allTrafficColumn);
    }

    @Override
    public String toString() {
        return "\nTableForJSON{" +
                "id=" + id +
                ", period='" + period + '\'' +
                ", trafficInAll=" + trafficInAll +
                ", secondLineTraffic=" + secondLineTraffic +
                ", allTrafficColumns=" + allTrafficColumns +
                ", bId=" + bId +
                '}';
    }
}
