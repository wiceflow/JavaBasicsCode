package com.wiceflow;

import com.wiceflow.companyLearn.subway.CVSTODB.TransferStation;
import com.wiceflow.companyLearn.subway.TransforMap.Dao;
import com.wiceflow.companyLearn.subway.TransforMap.GetWaitingTime;
import com.wiceflow.companyLearn.subway.TransforMap.NewGetWaitingTime;
import com.wiceflow.excel.util.poi.ImportExcel;
import com.wiceflow.util.ReadUtil;

import java.io.File;
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
//        int t = g.getWaitingTime(11,1,1);
//        System.out.println(t);
//        ReadUtil r = new ReadUtil();
//        String path = "F:\\票价\\换乘表.csv";
//        String path2 = "F:\\票价\\line.csv";
//        List<TransferStation> list = r.getTransferStation(path,path2);
//        Dao dao = new Dao();
//        try {
//            dao.saveTransferStation(list);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("失败");
//        }

//        GetWaitingTime g = new GetWaitingTime();
//        System.out.println(g.getWaitingTime(5, 9, 1));
//
//        NewGetWaitingTime g2 = new NewGetWaitingTime();
//        System.out.println(g2.getWaitingTime(5, 9, 180125175248L));


//        File excelFile = new File("F:\\换乘表.xlsx");
//        String fileName = excelFile.getName();
//        String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
//
//
//        System.out.println(fileName);
//        System.out.println(prefix);
        String path = "F:\\换乘表.xlsx";
        ImportExcel importExcel = new ImportExcel();
        try {
            List<List<Object>> lists = importExcel.parseExcel(path);
            for (int i = 0; i < lists.size(); i++) {
                List<Object> list = lists.get(i);
                for (int j = 0; j < list.size(); j++) {
                    System.out.print(list.get(j) + "   ");
                }
                System.out.print("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
