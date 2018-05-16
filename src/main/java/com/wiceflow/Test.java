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
import java.time.LocalTime;
import java.util.*;

/**
 * Created by BF on 2017/12/12.
 * 常用测试
 */
public class Test {
    //    public static void main(String[] args) throws ParseException {
//        File excel1 = new File("C:\\Users\\duxin\\Desktop\\dujia\\5号线总信息表20180425.csv");
//        try(FileInputStream fileInputStream = new FileInputStream(excel1)) {
//            Scanner scanner = new Scanner(fileInputStream,"gbk");
//            for (int i=1;i<=6;i++){
//                scanner.nextLine();
//            }
//            while (scanner.hasNext()){
//                String line = scanner.nextLine();
//                String[] lines = line.split(",");
//                //System.out.println(lines[0]);
//                String oo = lines[6];
//                System.out.println(oo);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
    public static void main(String[] args) {
        String a = "1 day";
        Timestamp time = Timestamp.valueOf(a);
        System.out.println(time);
    }
}
