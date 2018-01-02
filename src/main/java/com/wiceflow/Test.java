package com.wiceflow;

import com.wiceflow.companyLearn.subway.TransforMap.Dao;
import com.wiceflow.companyLearn.subway.TransforMap.GetWaitingTime;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BF on 2017/12/12.
 * 常用测试
 */
public class Test {
    public static void main(String[] args) {
        GetWaitingTime g = new GetWaitingTime();

        int time = g.getWaitingTime(9,5);
        System.out.println(time);
    }
}
