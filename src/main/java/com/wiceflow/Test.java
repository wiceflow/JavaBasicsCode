package com.wiceflow;
import com.wiceflow.json.fastjson.SaveDB;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.wiceflow.http.retrofit2.BDindex.getData;
import static java.lang.Thread.sleep;


/**
 * Created by BF on 2017/12/12.
 * 常用测试
 */
public class Test {
    public static void main(String[] args) {
        int d1 = 20170106;
        int d2 = 20171218;
        getData("20171212",true);
//        while (d1 <= d2) {
//            String date = String.valueOf(d1);
////            BDindexServer b= new BDindexServer();
////            b.getData(date,true);
//            SaveDB saveDB = new SaveDB(date);
//            Thread thread = new Thread(saveDB);
//            thread.start();
//            try {
//                sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//            Date dd = null;
//            try {
//                dd = formatter.parse(date);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            Calendar calendar = new GregorianCalendar();
//            calendar.setTime(dd);
//            calendar.add(calendar.DATE, 7);
//            dd = calendar.getTime();
//            date = formatter.format(dd);
//            d1 = Integer.valueOf(date);
//        }
//    }
}}
