package com.wiceflow.json.fastjson.po;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by BF on 2017/12/13.
 * 基础表 连接Table表和General表
 */
@Entity
@Table(name = "\"Basic\"", schema = "public", catalog = "indexSystem")
public class BasicCTwo {
    private int id;
    private Set<TableForJSON> tables = new HashSet<>();
    private Set<General> generals = new HashSet<>();
    // 期数
    private String period;
    // 日期
    private String date;
    private General general;
    private TableForJSON tfj;

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
    @Column(name = "\"Period\"")
    public String getPeriod() {
        return period;
    }
    public void setPeriod(String period) {
        this.period = period;
    }

    @OneToMany
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name = "\"bId\"")
    public Set<TableForJSON> getTables() {
        return tables;
    }
    public void setTables(Set<TableForJSON> tables) {
        this.tables = tables;
    }

    @OneToMany
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name = "\"bId\"")
    public Set<General> getGenerals() {
        return generals;
    }
    public void setGenerals(Set<General> generals) {
        this.generals = generals;
    }

    @Transient
    public General getGeneral() {
        general = generals.iterator().next();
        return general;
    }
    public void setGeneral(General general) {
        this.general = general;
        generals.add(general);
    }
    @Transient
    public TableForJSON getTfj() {
        tfj = tables.iterator().next();
        return tfj;
    }
    public void setTfj(TableForJSON tfj) {
        this.tfj = tfj;
        tables.add(tfj);
    }

    @Basic
    @Column(name = "date")
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BasicCTwo{" +
                "id=" + id +
                ", tables=" + tables +
                ", generals=" + generals +
                ", period='" + period + '\'' +
                ", date='" + date + '\'' +
                ", general=" + general +
                ", tfj=" + tfj +
                '}';
    }
}
