package com.wiceflow.quote;

/**
 * @author BF
 * @date 2020/5/31 0:49
 */
public class House {

    private String remark;

    public House(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "House{" +
                "remark='" + remark + '\'' +
                '}';
    }
}
