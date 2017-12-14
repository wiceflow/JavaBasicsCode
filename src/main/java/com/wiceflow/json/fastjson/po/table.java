package com.wiceflow.json.fastjson.po;

import java.util.Arrays;

/**
 * Created by BF on 2017/12/13.
 * 获取table字段json数据
 */
public class table {
    private String[][] area;
    private String[][] all;
    private String[][] cross;

    @Override
    public String toString() {
        return "table{" +
                "area=" + Arrays.toString(area) +
                ", all=" + Arrays.toString(all) +
                ", cross=" + Arrays.toString(cross) +
                '}';
    }

    public String[][] getArea() {
        return area;
    }

    public void setArea(String[][] area) {
        this.area = area;
    }



    public String[][] getAll() {
        return all;
    }

    public void setAll(String[][] all) {
        this.all = all;
    }

    public String[][] getCross() {
        return cross;
    }

    public void setCross(String[][] cross) {
        this.cross = cross;
    }
}
