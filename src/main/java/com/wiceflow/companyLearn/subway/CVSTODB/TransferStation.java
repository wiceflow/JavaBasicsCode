package com.wiceflow.companyLearn.subway.CVSTODB;

/**
 * Created by BF on 2018/1/4.
 * 换乘实体类
 */
public class TransferStation {
    private int id;
    private int startLine;
    private int endLine;
    private int waitingTime;

    public TransferStation(int startLine, int endLine) {
        this.startLine = startLine;
        this.endLine = endLine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    @Override
    public String toString() {
        return "TransferStation{" +
                "id=" + id +
                ", startLine=" + startLine +
                ", endLine=" + endLine +
                ", waitingTime=" + waitingTime +
                '}';
    }

    /**
     * 重写equals方法，若startline与endline都相等则判为同一对象
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferStation that = (TransferStation) o;

        if (startLine != that.startLine) return false;
        return endLine == that.endLine;
    }

    @Override
    public int hashCode() {
        int result = startLine;
        result = 31 * result + endLine;
        return result;
    }
}
