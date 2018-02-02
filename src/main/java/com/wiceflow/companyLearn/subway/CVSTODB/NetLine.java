package com.wiceflow.companyLearn.subway.CVSTODB;

/**
 * @author BF
 */
public class NetLine {
    private Long id;
    /**
     * 版本号
     */
    private Long confVersion;
    /**
     * 候车时间
     */
    private Double departInterval;
    /**
     * 是否商务
     */
    private Integer isBusiness;

    /**
     * 线路号
     */
    private String lineNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConfVersion() {
        return confVersion;
    }

    public void setConfVersion(Long confVersion) {
        this.confVersion = confVersion;
    }

    public Double getDepartInterval() {
        return departInterval;
    }

    public void setDepartInterval(Double departInterval) {
        this.departInterval = departInterval;
    }

    public Integer getIsBusiness() {
        return isBusiness;
    }

    public void setIsBusiness(Integer isBusiness) {
        this.isBusiness = isBusiness;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo == null ? null : lineNo.trim();
    }

    @Override
    public String toString() {
        return "NetLine{" +
                "confVersion=" + confVersion +
                ", departInterval=" + departInterval +
                ", isBusiness=" + isBusiness +
                ", lineNo='" + lineNo + '\'' +
                '}';
    }
}