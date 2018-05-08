package com.wiceflow;

import com.alibaba.fastjson.JSON;
import com.wiceflow.companyLearn.subway.CVSTODB.TransferStation;
import com.wiceflow.companyLearn.subway.TransforMap.Dao;
import com.wiceflow.companyLearn.subway.TransforMap.GetWaitingTime;
import com.wiceflow.companyLearn.subway.TransforMap.NewGetWaitingTime;
import com.wiceflow.excel.util.poi.ImportExcel;
import com.wiceflow.util.ReadUtil;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by BF on 2017/12/12.
 * 常用测试
 */
public class Test {
    public static void main(String[] args) throws ParseException {
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.parse("2018-03-28" + " 00:00:00.0"));
//        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        String d = sdf2.format(date);
//        String d2 = d.replaceAll("-","");
//        String d3 = d2.replaceAll(":","").replaceAll(" ","");
//        Number number = NumberFormat.getInstance().parse(d3);
//        System.out.println(d3);
//        System.out.println(number);

        //String str = JSON.toJSONString([{"confVersion":180401181701,"fareType":100,"id":"","billingUnit":0,"baseStartingMile":0,"baseStartingFare":0,"segments":1,"fareSegments":[{"startingPoint":0,"endingPoint":0,"baseChargeRate":0,"baseChargingUnit":0,"id":null,"basisId":180401181701}]},{"confVersion":180401181701,"fareType":101,"id":"","billingUnit":0,"baseStartingMile":0,"baseStartingFare":0,"businessStartingFare":0,"fareRate":2,"segments":1,"fareSegments":[{"startingPoint":0,"endingPoint":0,"baseChargeRate":0,"baseChargingUnit":0,"businessChargeRate":0,"businessChargingUnit":0,"id":null,"basisId":180401181701}]}]");
        //System.out.println(str);

    }
}
