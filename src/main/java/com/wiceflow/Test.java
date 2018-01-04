package com.wiceflow;

import com.wiceflow.companyLearn.subway.CVSTODB.TransferStation;
import com.wiceflow.companyLearn.subway.TransforMap.Dao;
import com.wiceflow.companyLearn.subway.TransforMap.GetWaitingTime;
import com.wiceflow.util.ReadUtil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BF on 2017/12/12.
 * 常用测试
 */
public class Test {
    public static void main(String[] args) {
//        GetWaitingTime g = new GetWaitingTime();
//
//        int time = g.getWaitingTime(2,11,1);
//        System.out.println(time);
        ReadUtil r = new ReadUtil();
        String path = "F:\\票价\\换乘表.csv";
        String path2 = "F:\\票价\\线路表.csv";
        List<TransferStation> list = r.getTransferStation(path,path2);
        Dao dao = new Dao();
        try {
            dao.saveTransferStation(list);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("失败");
        }
    }
}
