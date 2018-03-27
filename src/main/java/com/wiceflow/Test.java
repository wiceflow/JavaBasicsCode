package com.wiceflow;

import com.wiceflow.companyLearn.subway.CVSTODB.TransferStation;
import com.wiceflow.companyLearn.subway.TransforMap.Dao;
import com.wiceflow.companyLearn.subway.TransforMap.GetWaitingTime;
import com.wiceflow.companyLearn.subway.TransforMap.NewGetWaitingTime;
import com.wiceflow.excel.util.poi.ImportExcel;
import com.wiceflow.util.ReadUtil;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by BF on 2017/12/12.
 * 常用测试
 */
public class Test {
    public static void main(String[] args) {
        Map dataMap = null;
        List list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            dataMap = (Map) list.get(i);
        }

        Iterator iterator = dataMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println(entry.getKey() + " :" + entry.getValue());
        }

    }
}
