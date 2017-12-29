package com.wiceflow.companyLearn.subway;

import javax.persistence.*;

/**
 * Created by BF on 2017/12/29.
 * 对应数据库 t_waiting_time表
 */
@Entity
@Table(name = "t_waiting_time")
public class WaitingTime {
    private int id;
    private int startLine;
    private int endLine;
    private double waitingTime;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "start_line")
    public int getStartLine() {
        return startLine;
    }
    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }
    @Column(name = "end_line")
    public int getEndLine() {
        return endLine;
    }
    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }
    @Column(name = "waiting_time")
    public double getWaitingTime() {
        return waitingTime;
    }
    public void setWaitingTime(double waitingTime) {
        this.waitingTime = waitingTime;
    }
}
