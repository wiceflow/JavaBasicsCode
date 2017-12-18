package com.wiceflow;
import org.quartz.SchedulerException;
import java.text.ParseException;

import static com.wiceflow.http.retrofit2.BDindex.ObtainData;
import static com.wiceflow.http.retrofit2.BDindex.getData;



/**
 * Created by BF on 2017/12/12.
 * 常用测试
 */
public class Test {
    public static void main(String[] args) throws ParseException, SchedulerException {


        getData("20170106",true);
        //timer();
        //ObtainData();
    }
}
