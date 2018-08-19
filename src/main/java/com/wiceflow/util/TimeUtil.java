package com.wiceflow.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by BF on 2017/12/14.
 * 日期工具类
 */
public final class TimeUtil {

    /**
     * 返回当前时间的Int类型日期
     * @return 20171214
     */
    public static int returnIntDate(){
        Date date = new Date();
        return returnIntDate(date);
    }

    /**
     * 返回当前时间的Int类型日期
     * @param date [Date] 需要一个Date类型参数
     * @return 20171214
     */
    public static int returnIntDate(Date date){
        DateFormat dateformat =new SimpleDateFormat("yyyyMMdd");
        String str =dateformat.format(date);
        return Integer.valueOf(str);
    }

    /**
     * 返回当前日期的Int类型日期
     * @param date 传入一个[String] 格式规范 2017-12-14 或者 2017/12/14 或者 混合
     * @return
     */
    public static int returnIntDate(String date){
        date = date.replaceAll("/","");
        date = date.replaceAll("-","");
        date = date.replaceAll("\\\\","");
        return Integer.valueOf(date);
    }

}
