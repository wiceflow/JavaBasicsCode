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
import java.io.FileInputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by BF on 2017/12/12.
 * 常用测试
 */
public class Test {
    public static void main(String[] args) {
        String s = "         ";
        System.out.println(s.trim().length());
    }
}