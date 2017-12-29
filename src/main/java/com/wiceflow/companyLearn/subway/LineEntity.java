package com.wiceflow.companyLearn.subway;

import javax.persistence.*;

/**
 * Created by BF on 2017/12/29.
 */
@Entity
@Table(name = "t_line")
public class LineEntity {
    private int id;
    private String lineNo;
    private String lineName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "line_no")
    public String getLineNo() {
        return lineNo;
    }
    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }
    @Column(name = "line_name")
    public String getLineName() {
        return lineName;
    }
    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    @Override
    public String toString() {
        return "LineEntity{" +
                "id=" + id +
                ", lineNo='" + lineNo + '\'' +
                ", lineName='" + lineName + '\'' +
                '}';
    }
}
